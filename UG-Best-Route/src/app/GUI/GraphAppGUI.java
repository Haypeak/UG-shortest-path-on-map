package app.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import app.graph.Graph;
import app.graph.Node;
import app.algorithms.Dijkstra;
import java.util.ArrayList;

public class GraphAppGUI extends JFrame {
    private static Graph graph;

    public static void setGraph(Graph graph) {
        GraphAppGUI.graph = graph;
    }

    public GraphAppGUI() {
        setTitle("Campus Map");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        JLabel welcomeLabel = new JLabel("Welcome to the Campus Map App!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.BLACK); // White text color
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text

        JLabel startLabel = new JLabel("Select your start point:");
        startLabel.setFont(new Font("Arial", Font.BOLD, 14));
        startLabel.setForeground(new Color(50, 50, 150));

        JComboBox<String> startComboBox = new JComboBox<>();
        startComboBox.setBackground(Color.WHITE);

        JLabel endLabel = new JLabel("Select your destination:");
        endLabel.setFont(new Font("Arial", Font.BOLD, 14));
        endLabel.setForeground(new Color(50, 50, 150));

        JComboBox<String> endComboBox = new JComboBox<>();
        endComboBox.setBackground(Color.WHITE);

        JButton findPathButton = new JButton("Find Shortest Path");
        findPathButton.setBackground(new Color(70, 130, 180));
        findPathButton.setForeground(Color.WHITE);

        JTextArea resultTextArea = new JTextArea(5, 20);
        resultTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultTextArea.setBackground(Color.LIGHT_GRAY); // Set background color to light gray
        resultTextArea.setForeground(Color.DARK_GRAY);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true); // Enable line wrap
        resultTextArea.setWrapStyleWord(true); // Wrap at word boundaries
        resultTextArea.setPreferredSize(new Dimension(550, 100)); // Increase width to 550 pixels

        // Populate combo boxes with node names
        for (Node node : graph.getNodes()) {
            startComboBox.addItem(node.getName());
            endComboBox.addItem(node.getName());
        }

        // Set up button action
        findPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startNodeName = (String) startComboBox.getSelectedItem();
                String endNodeName = (String) endComboBox.getSelectedItem();
                if (startNodeName != null && endNodeName != null) {
                    Node startNode = graph.getNodeByName(startNodeName);
                    Node endNode = graph.getNodeByName(endNodeName);
                    if (startNode != null && endNode != null) {
                        ArrayList<Node> shortestPath = Dijkstra.findShortestPath(graph, startNode, endNode);
                        resultTextArea.setText("The Shortest Path is: " + shortestPath.toString() +
                                "\nThe Total Distance that will be covered is: " + Dijkstra.getDistance(endNode));
                    }
                }
            }
        });

        // Set up layout with better spacing and alignment
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow stretching horizontally
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc); // Add the welcome message

        gbc.gridy = 1;
        panel.add(startLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(startComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(endLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(endComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(findPathButton, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow horizontal stretching for text area
        panel.add(new JScrollPane(resultTextArea), gbc);

        add(panel);
        getContentPane().setBackground(Color.RED); // Change the background color of the entire application to red

        // Make the window visible
        setVisible(true);
    }
}
