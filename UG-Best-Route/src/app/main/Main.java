package app.main;

import app.GUI.GraphAppGUI;
import app.graph.Edge;
import app.graph.Graph;
import app.graph.Node;

public class Main {
    static Graph graph = new Graph();

    public static void main(String[] args) {
        // Display the welcoming message centered
        displayWelcomeMessage();

        // Nodes in the graph.
        Node gym = new Node("Gym");
        Node diaspora = new Node("Diaspora");
        Node ish = new Node("ISH");
        Node nightMarket = new Node("Night Market");
        Node sarbahHall = new Node("Sarbah Hall");
        Node commonWealth = new Node("Common Wealth");
        Node greatHall = new Node("Great Hall");
        Node legonHall = new Node("Legon Hall");
        Node akuafoHall = new Node("Akuafo Hall");
        Node voltaHall = new Node("Volta Hall");
        Node balmeLibrary = new Node("Balme Library");
        Node cbas = new Node("CBAS");
        Node mainGate = new Node("Main Gate");
        Node jqb = new Node("JQ Building");
        Node lawSchool = new Node("Law School");
        Node busSchool = new Node("Business School");
        Node gcb = new Node("GCB");
        Node csdepartment = new Node("CS Department");
        Node politicalScienceDepartment = new Node("Political Science Department");
        Node nb = new Node("NB");
        Node nnb = new Node("NNB");

        graph.addEdge(new Edge(gym, diaspora, 500, 10));
        graph.addEdge(new Edge(gym, ish, 415, 8));
        graph.addEdge(new Edge(gym, nightMarket, 634, 9));

        graph.addEdge(new Edge(diaspora, ish, 400, 8));
        graph.addEdge(new Edge(ish, nightMarket, 214, 3));

        graph.addEdge(new Edge(nightMarket, commonWealth, 1025, 20));
        graph.addEdge(new Edge(nightMarket, legonHall, 914, 17));
        graph.addEdge(new Edge(nightMarket, sarbahHall, 350, 5));

        graph.addEdge(new Edge(commonWealth, greatHall, 515, 9));
        graph.addEdge(new Edge(commonWealth, voltaHall, 440, 5));
        graph.addEdge(new Edge(commonWealth, legonHall, 460, 5));

        graph.addEdge(new Edge(sarbahHall, legonHall, 630, 12));
        graph.addEdge(new Edge(sarbahHall, akuafoHall, 460, 8));

        graph.addEdge(new Edge(legonHall, akuafoHall, 583, 7));
        graph.addEdge(new Edge(legonHall, balmeLibrary, 530, 6));
        graph.addEdge(new Edge(legonHall, voltaHall, 260, 3));

        graph.addEdge(new Edge(akuafoHall, cbas, 385, 5));
        graph.addEdge(new Edge(akuafoHall, csdepartment, 780, 13));
        graph.addEdge(new Edge(akuafoHall, balmeLibrary, 580, 7));

        graph.addEdge(new Edge(cbas, mainGate, 624, 6));
        graph.addEdge(new Edge(cbas, jqb, 610, 9));
        graph.addEdge(new Edge(jqb, lawSchool, 466, 5));

        graph.addEdge(new Edge(lawSchool, csdepartment, 384, 4));

        graph.addEdge(new Edge(balmeLibrary, lawSchool, 960, 18));
        graph.addEdge(new Edge(balmeLibrary, busSchool, 203, 4));

        graph.addEdge(new Edge(voltaHall, busSchool, 390, 3));
        graph.addEdge(new Edge(voltaHall, balmeLibrary, 415, 5));

        graph.addEdge(new Edge(busSchool, gcb, 433, 5));
        graph.addEdge(new Edge(busSchool, nb, 424, 5));
        graph.addEdge(new Edge(busSchool, csdepartment, 389, 4));

        graph.addEdge(new Edge(politicalScienceDepartment, csdepartment, 386, 4));
        graph.addEdge(new Edge(politicalScienceDepartment, nb, 204, 3));
        graph.addEdge(new Edge(nb, nnb, 330, 4));
        graph.addEdge(new Edge(nnb, gcb, 160, 3));

        // Set the graph in the GUI and launch it
        GraphAppGUI.setGraph(graph);
        new GraphAppGUI();
    }

    public static void displayWelcomeMessage() {
        String message = "\nWelcome to the UG Campus Map Application! \n \nPlease move to the GUI App to enjoy the full experience, Thank You.";
        int width = 80; // Adjust width as needed
        int padding = (width - message.length()) / 2;
        String paddedMessage = String.format("%" + (padding + message.length()) + "s", message);
        System.out.println(); // Print a newline before the message
        System.out.println(paddedMessage);
        System.out.println();
    }
}
