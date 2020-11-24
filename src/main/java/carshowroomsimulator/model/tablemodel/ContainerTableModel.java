package carshowroomsimulator.model.tablemodel;

import carshowroomsimulator.model.CarShowroom;
import carshowroomsimulator.model.CarShowroomContainer;

import javax.swing.table.AbstractTableModel;
import java.util.*;

public class ContainerTableModel extends AbstractTableModel {
    Map<String, CarShowroom> carShowrooms;
    CarShowroomContainer carShowroomContainer = new CarShowroomContainer(carShowrooms);
    private final static Object[] columnNames = {"ID", "NAME", "CAPACITY"};

    private final static int ID = 0;
    private final static int NAME = 1;
    private final static int CAPACITY = 2;

    public ContainerTableModel() {}

    @Override
    public int getRowCount() {
        if(carShowrooms ==null) return 0;
        return carShowrooms.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if(carShowrooms == null) return null;
        CarShowroom p = (new ArrayList<CarShowroom>(carShowrooms.values())).get(rowIndex);
        switch (columnIndex) {
            case ID:
                return rowIndex + 1;
            case NAME:
                return p.getShowroomName();
            case CAPACITY:
                return p.getMaxShowroomCapacity();
            default:
                return p;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column].toString();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void setModelData(Map<String, CarShowroom> carShowrooms) {
        this.carShowrooms =  carShowrooms;
    }
    public CarShowroom getPerson(int position) {
        return carShowrooms.get(position);
    }

}