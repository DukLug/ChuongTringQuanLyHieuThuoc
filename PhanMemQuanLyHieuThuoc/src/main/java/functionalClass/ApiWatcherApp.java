package functionalClass;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiWatcherApp extends JFrame {
    private static final String API_URL = "https://670717a7a0e04071d22919b3.mockapi.io/SanPhamDuocQuet";
    private JTextArea textArea;
    private JButton stopButton;
    private volatile boolean running = true;

    public ApiWatcherApp() {
        setTitle("API Watcher");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        
        stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;  // Stop the API polling
                textArea.append("\nStopped watching API.");
            }
        });

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(stopButton, BorderLayout.SOUTH);

        // Start polling API in a separate thread
        new Thread(this::pollApi).start();
    }

    private void pollApi() {
        while (running) {
            try {
                // Fetch and display "maVach" values
                fetchAndDisplayMaVach();
                Thread.sleep(1000);  // Poll every 5 seconds
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void fetchAndDisplayMaVach() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // Parse JSON and update JTextArea
            JSONArray jsonArray = new JSONArray(response.toString());
            SwingUtilities.invokeLater(() -> {
                textArea.setText("");  // Clear previous content
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    textArea.append(jsonObject.getString("maVach") + "\n");
                }
            });
        } else {
            System.out.println("GET request failed: " + connection.getResponseCode());
        }
        
        connection.disconnect();
    }

    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ApiWatcherApp frame = new ApiWatcherApp();
            frame.setVisible(true);
        });
    }
    */
}
