package net.betterpvp.osFighter.gui;

import javax.swing.table.DefaultTableModel;

public class AntibanTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;

	public AntibanTableModel(){
		super(new String[]{"Anti Ban", "Enabled"}, 0);
	}


	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> clazz = String.class;
		switch (columnIndex) {
			case 0:
				clazz = String.class;
				break;
			case 1:
				clazz = Boolean.class;
				break;
		}
		return clazz;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 1;
	}

}
