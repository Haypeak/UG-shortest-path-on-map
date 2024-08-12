package app.GUI;

import app.algorithms.Dijkstra;
import app.graph.Graph;
import app.graph.Node;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class GraphAppGUI extends JFrame {
    private static Graph graph;

    public static void setGraph(Graph graph) {
        GraphAppGUI.graph = graph;
    }

    public GraphAppGUI() {
        setTitle("UG Campus Map");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        JLabel welcomeLabel = new JLabel("Welcome to the UG Campus Map App!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

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
        resultTextArea.setFont(new Font("Monospaced", Font.BOLD, 12));
        resultTextArea.setBackground(Color.LIGHT_GRAY);
        resultTextArea.setForeground(Color.DARK_GRAY);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setPreferredSize(new Dimension(550, 100));

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
                        if (startNode.equals(endNode)) {
                            // Display error message if start and end nodes are the same
                            resultTextArea.setText("Error: Your start point and destination must differ.");
                        } else {
                            ArrayList<Node> shortestPath = Dijkstra.findShortestPath(graph, startNode, endNode);
                            
                            // Modify the shortest path output
                            StringBuilder pathBuilder = new StringBuilder("The Shortest Path is: ");
                            for (int j = 0; j < shortestPath.size(); j++) {
                                if (j == 0) {
                                    pathBuilder.append("[ Move from ").append(shortestPath.get(j).getName());
                                } else if (j == shortestPath.size() - 1) {
                                    pathBuilder.append(" -> End at the ").append(shortestPath.get(j).getName());
                                } else {
                                    pathBuilder.append(" -> ").append(shortestPath.get(j).getName());
                                }
                            }
                            pathBuilder.append(" ]");

                            // Update the text area with the modified path
                            resultTextArea.setText(pathBuilder.toString() +
                                                   "\nThe Total Distance that will be covered is: " + Dijkstra.getDistance(endNode));
                        }
                    }
                }
            }
        });

        // Set up layout with better spacing and alignment
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JScrollPane(resultTextArea), gbc);

        add(panel);
        getContentPane().setBackground(Color.RED);

        // Make the window visible
        setVisible(true);
    }
}
