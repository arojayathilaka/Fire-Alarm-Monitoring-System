/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DS_Project;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Kesara
 */
public class Table_Colour extends DefaultTableCellRenderer {
    
    //If the smoke level or CO2 level is above 5 they will be marked in red

    public Table_Colour() {
    }

    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);
        
        Color back = Color.WHITE;

        Object obj = jtable.getValueAt(row, 3);
        Object ob = jtable.getValueAt(row, 4);

        try {
            int co2 = Integer.parseInt(obj.toString());
            int smoke = Integer.parseInt(ob.toString());

            if (co2 > 5 || smoke > 5) {
                back = Color.RED;
            }

        } catch (Exception e) {

        }

        label.setBackground(back);
        return label;
    }

}
