package carshowroomsimulator;

import carshowroomsimulator.data.DataGenerator;
import carshowroomsimulator.model.CarShowroom;
import carshowroomsimulator.model.CarShowroomContainer;
import carshowroomsimulator.model.ItemCondition;
import carshowroomsimulator.model.Vehicle;
import carshowroomsimulator.model.tablemodel.ContainerTableModel;
import carshowroomsimulator.model.tablemodel.ShowroomModel;

import javax.swing.*;
import java.util.LinkedHashMap;

public class View extends JFrame {
    ContainerTableModel model;
    String Data = null;
    DataGenerator dataGenerator;
    private JPanel panel;
    private JButton addCenterButton;
    private JButton removeCenterButton;
    private JButton addCarButton;
    private JButton removeCarButton;
    private JTable carShowroomsTable;
    private JTable carsTable;
    private JButton sortCentersByCurrentButton;
    private JLabel carShowroomsList;
    private JLabel showroomCarsList;
    private JTable carsShowroomTable;
    private JScrollPane carsScrollPane;
    private JScrollPane showroomsScrollPane;
    private CarShowroomContainer showrooms;
    private ShowroomModel model2;
    private ListSelectionModel select;

    public View() {

        dataGenerator = DataGenerator.getInstance();
        //  DataGenerator dataGenerator2 = DataGenerator.getInstance();

        System.out.println(dataGenerator.getCarShowroomLinkedHashMap().entrySet());
        //    System.out.println(dataGenerator2.getCarShowroomLinkedHashMap().entrySet());

        showrooms = new CarShowroomContainer(dataGenerator.getCarShowroomLinkedHashMap());

        model = new ContainerTableModel();

        model.setModelData(showrooms.getShowroomMap());
        carsShowroomTable = new JTable(model);
        showroomsScrollPane.setViewportView(carsShowroomTable);

        model2 = new ShowroomModel();
        carsTable = new JTable(model2);
        carsScrollPane.setViewportView(carsTable);

        select = carsShowroomTable.getSelectionModel();
        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        initView();


    }

    public void initView(){
        addCenterButton.addActionListener(e -> addCenter());

        removeCenterButton.addActionListener(e -> removeCenter());

        addCarButton.addActionListener(e -> addCar());

        removeCarButton.addActionListener(e -> removeCar());

        sortCentersByCurrentButton.addActionListener(e -> sortCenters());

        select.addListSelectionListener(e -> changeCarsTable());
    }

    private void changeCarsTable() {
        int[] row = carsShowroomTable.getSelectedRows();
        for (int k : row) {
            Data = (String) carsShowroomTable.getValueAt(k, 1);
            model2.setModelData(showrooms.findShowroomByName(String.valueOf(Data)).getCarList());
            model2.fireTableDataChanged();

        }
    }

    private void sortCenters() {
        model.setModelData(CarShowroomContainer.sortByValue((LinkedHashMap<String, CarShowroom>) showrooms.getShowroomMap()));
        model.fireTableDataChanged();
    }

    private void removeCar() {
        String input = JOptionPane.showInputDialog("Enter car name to remove:");

        showrooms.findShowroomByName(String.valueOf(Data)).removeProduct(input);
        model2.fireTableDataChanged();
    }

    private void addCar() {
        String inputBrand = JOptionPane.showInputDialog("Enter car brand:");
        String inputModel = JOptionPane.showInputDialog("Enter car model:");
        ItemCondition inputCondition = ItemCondition.valueOf(JOptionPane.showInputDialog("Enter car condition:"));
        double inputPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter car price:"));
        int inputYear = Integer.parseInt(JOptionPane.showInputDialog("Enter car year of production:"));
        double inputMileage = Double.parseDouble(JOptionPane.showInputDialog("Enter car mileage:"));
        double inputCapacity = Double.parseDouble(JOptionPane.showInputDialog("Enter car engine capacity:"));

        System.out.println(Data);
        showrooms.findShowroomByName(String.valueOf(Data)).addProduct(new Vehicle(
                inputBrand,
                inputModel,
                inputCondition,
                inputPrice,
                inputYear,
                inputMileage,
                inputCapacity,
                1));
        model2.fireTableDataChanged();
    }

    private void removeCenter() {
        String input = JOptionPane.showInputDialog("Enter Center name to remove:");

        showrooms.removeCenter(input);
        model.setModelData(showrooms.getShowroomMap());
        model.fireTableDataChanged();
    }

    private void addCenter() {
        String inputName = JOptionPane.showInputDialog("Enter Center name to add:");
        int inputCapacity = Integer.parseInt(JOptionPane.showInputDialog("Enter Center capacity:"));

        showrooms.addCenter(inputName, inputCapacity);
        model.setModelData(showrooms.getShowroomMap());
        model.fireTableDataChanged();
    }

}