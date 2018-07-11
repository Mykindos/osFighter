package net.betterpvp.osFighter.gui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.FighterNPC;

/**
 *
 * @author Tom
 */
public class GUI extends JFrame {

	// Variables declaration - do not modify
	private ButtonGroup bgLooting;
	private JButton btnAdd;
	private JButton btnAddBankItem;
	private JButton btnAddTask1;
	private JButton btnAddTask2;
	private JButton btnClearBankList;
	private JButton btnClearCurrent;
	private JButton btnClearCurrent2;
	private JButton btnLoad;
	private JButton btnLootAdd;
	private JButton btnLootRemove;
	private Button btnRandomLoop;
	private JButton btnRefresh;
	private JButton btnRemove;
	private JButton btnRemoveSelectedBankItem;
	private JButton btnRemoveSelectedTask;
	private JButton btnSave;
	private JButton buttonStart;
	private JCheckBox chkAntidotePotion;
	private JCheckBox chkAntifirePotion;
	private JCheckBox chkAntiposionPotion;
	private JCheckBox chkAntivenomPotion;
	private JCheckBox chkAttackPotion;
	private JCheckBox chkBankNoPrayerPots;
	private JCheckBox chkBanking;
	private JCheckBox chkCombatPotion;
	private JCheckBox chkDefencePotion;
	private JCheckBox chkDepositInventory;
	private JCheckBox chkDrinksEnabled;
	private JCheckBox chkFoodEnabled;
	private JCheckBox chkItemReplaceable;
	private JCheckBox chkMagicPotion;
	private JCheckBox chkOtherHealing;
	private JCheckBox chkRangingPotion;
	private JCheckBox chkSafeSpot;
	private JCheckBox chkStrengthPotion;
	private JCheckBox chkUseCannon;
	private JCheckBox chkUseGuthans;
	private JCheckBox chkUseSGS;
	private JComboBox<String> cmbTaskAttackStyle;
	private JCheckBox jCheckBox1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JList<String> jList1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane5;
	private JScrollPane jScrollPane6;
	private JSpinner jSpinner1;
	private JSpinner jSpinner2;
	private JPanel jpAntiban;
	private JPanel jpConfig;
	private JPanel jpMain;
	private JPanel jpOther;
	private JPanel jpScheduler;
	private JPanel jpSpeed;
	private JScrollBar jsbMaxSpeed;
	private JScrollBar jsbMinSpeed;
	private JTable jtAntiban;
	private JLabel lblBankWithdrawAmount;
	private JLabel lblCurrentItems;
	private Label lblCurrentMaxSpeed;
	private Label lblCurrentMinSpeed;
	private JLabel lblEatBelow;
	private JLabel lblEatBelowDeviation;
	private JLabel lblEatBelowHealth;
	private JLabel lblExplainPotions;
	private JLabel lblFoodToEat;
	private JLabel lblGuthansBelowHealth;
	private JLabel lblGuthansUseBelow;
	private JLabel lblLogo;
	private JLabel lblLootConditions;
	private JLabel lblLootother;
	private JLabel lblMagicPotions;
	private Label lblMaxSpeed;
	private JLabel lblMeleePotions;
	private Label lblMinSpeed;
	private JLabel lblORFilter;
	private JLabel lblOtherPotions;
	private JLabel lblPrayerDrinkBelow;
	private JLabel lblPrayerPoints;
	private JLabel lblRangingPotions;
	private JLabel lblSelectTargets;
	private JLabel lblSelectTargets2;
	private JLabel lblSpeedWarning;
	private JLabel lblTargetList;
	private JLabel lblTargetName;
	private JLabel lblTaskEquip;
	private JLabel lblTaskTrain;
	private JLabel lblTaskUntilLevel;
	private JLabel lblWithdrawList;
	private DefaultListModel<FighterNPC> nearbyModel, currentModel;
	private JList<String> listLootingItems;
	private JList<FighterNPC> listNearbyTargets;
	private JList<FighterNPC> listCurrentTargets;
	private JList<String> listTasks;
	private JPanel pnlAddItems;
	private JPanel pnlBank;
	private JPanel pnlDrinks;
	private JPanel pnlFood;
	private JPanel pnlFood1;
	private JPanel pnlFoodDrinks;
	private JPanel pnlItemList;
	private JPanel pnlLoot;
	private JPanel pnlLootConditions;
	private JTabbedPane pnlMain;
	private JPanel pnlMonsterList;
	private JPanel pnlMonsterSelection;
	private JPanel pnlPrayer;
	private JPanel pnlSubBanking;
	private JPanel pnlSubPrayer;
	private JRadioButton rbLootHighAlch;
	private JRadioButton rbLootLowAlch;
	private JRadioButton rbLootNone;
	private JRadioButton rbLootNoted;
	private JSpinner spinnerEatDeviation;
	private JSpinner spinnerEatHealth;
	private JSpinner spinnerGuthansHealth;
	private JSpinner spinnerMinPrayer;
	private JTable tablePrayers;
	private JTextField txtFoodToEat;
	private JTextField txtItemName;
	private JTextField txtItemToWithdraw;
	private JTextField txtTargetName;
	private JTextField txtTaskItemToEquip;
	private static final long serialVersionUID = 1L;



	private Fighter instance;
	/**
	 * Creates new form GUI
	 */
	public GUI(Fighter instance) {
		this.instance = instance;
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT
	 * modify this code. The content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		nearbyModel = new DefaultListModel<FighterNPC>();
		currentModel = new DefaultListModel<FighterNPC>();
		bgLooting = new ButtonGroup();
		lblLogo = new JLabel();
		buttonStart = new JButton();
		pnlMain = new JTabbedPane();
		jpMain = new JPanel();
		pnlMonsterSelection = new JPanel();
		lblSelectTargets = new JLabel();
		jScrollPane1 = new JScrollPane();
		listNearbyTargets = new JList<FighterNPC>(nearbyModel);
		listCurrentTargets = new JList<FighterNPC>(currentModel);
		lblORFilter = new JLabel();
		lblTargetName = new JLabel();
		txtTargetName = new JTextField();
		btnRefresh = new JButton();
		pnlMonsterList = new JPanel();
		lblTargetList = new JLabel();
		jScrollPane2 = new JScrollPane();

		btnClearCurrent = new JButton();
		btnRemove = new JButton();
		btnAdd = new JButton();
		pnlFoodDrinks = new JPanel();
		pnlFood = new JPanel();
		lblEatBelow = new JLabel();
		spinnerEatHealth = new JSpinner();
		lblEatBelowHealth = new JLabel();
		lblEatBelowDeviation = new JLabel();
		spinnerEatDeviation = new JSpinner();
		lblFoodToEat = new JLabel();
		txtFoodToEat = new JTextField();
		pnlDrinks = new JPanel();
		chkAntiposionPotion = new JCheckBox();
		chkAntifirePotion = new JCheckBox();
		chkMagicPotion = new JCheckBox();
		chkRangingPotion = new JCheckBox();
		chkDefencePotion = new JCheckBox();
		chkStrengthPotion = new JCheckBox();
		chkAttackPotion = new JCheckBox();
		lblExplainPotions = new JLabel();
		lblMeleePotions = new JLabel();
		chkCombatPotion = new JCheckBox();
		lblOtherPotions = new JLabel();
		lblMagicPotions = new JLabel();
		lblRangingPotions = new JLabel();
		chkAntivenomPotion = new JCheckBox();
		chkAntidotePotion = new JCheckBox();
		chkDrinksEnabled = new JCheckBox();
		chkOtherHealing = new JCheckBox();
		chkFoodEnabled = new JCheckBox();
		pnlFood1 = new JPanel();
		chkUseSGS = new JCheckBox();
		chkUseGuthans = new JCheckBox();
		lblGuthansUseBelow = new JLabel();
		spinnerGuthansHealth = new JSpinner();
		lblGuthansBelowHealth = new JLabel();
		pnlLoot = new JPanel();
		pnlAddItems = new JPanel();
		lblSelectTargets2 = new JLabel();
		jLabel1 = new JLabel();
		txtItemName = new JTextField();
		lblLootother = new JLabel();
		pnlLootConditions = new JPanel();
		rbLootNone = new JRadioButton();
		rbLootNoted = new JRadioButton();
		rbLootLowAlch = new JRadioButton();
		rbLootHighAlch = new JRadioButton();
		lblLootConditions = new JLabel();
		chkItemReplaceable = new JCheckBox();
		pnlItemList = new JPanel();
		lblCurrentItems = new JLabel();
		jScrollPane6 = new JScrollPane();
		listLootingItems = new JList<>();
		btnClearCurrent2 = new JButton();
		btnLootRemove = new JButton();
		btnLootAdd = new JButton();
		pnlBank = new JPanel();
		chkBanking = new JCheckBox();
		pnlSubBanking = new JPanel();
		chkDepositInventory = new JCheckBox();
		lblBankWithdrawAmount = new JLabel();
		jSpinner2 = new JSpinner();
		txtItemToWithdraw = new JTextField();
		jScrollPane5 = new JScrollPane();
		jList1 = new JList<>();
		btnAddBankItem = new JButton();
		btnClearBankList = new JButton();
		btnRemoveSelectedBankItem = new JButton();
		lblWithdrawList = new JLabel();
		pnlPrayer = new JPanel();
		jCheckBox1 = new JCheckBox();
		pnlSubPrayer = new JPanel();
		lblPrayerDrinkBelow = new JLabel();
		spinnerMinPrayer = new JSpinner();
		lblPrayerPoints = new JLabel();
		chkBankNoPrayerPots = new JCheckBox();
		jScrollPane4 = new JScrollPane();
		tablePrayers = new JTable();
		jpOther = new JPanel();
		chkUseCannon = new JCheckBox();
		chkSafeSpot = new JCheckBox();
		jpScheduler = new JPanel();
		jScrollPane3 = new JScrollPane();
		listTasks = new JList<>();
		btnAddTask2 = new JButton();
		lblTaskUntilLevel = new JLabel();
		lblTaskTrain = new JLabel();
		cmbTaskAttackStyle = new JComboBox<>();
		jSpinner1 = new JSpinner();
		btnAddTask1 = new JButton();
		btnRemoveSelectedTask = new JButton();
		lblTaskEquip = new JLabel();
		txtTaskItemToEquip = new JTextField();
		jpAntiban = new JPanel();
		jtAntiban = new JTable();
		jLabel2 = new JLabel();
		jpSpeed = new JPanel();
		lblMinSpeed = new Label();
		jsbMinSpeed = new JScrollBar();
		lblCurrentMinSpeed = new Label();
		lblMaxSpeed = new Label();
		jsbMaxSpeed = new JScrollBar();
		lblCurrentMaxSpeed = new Label();
		btnRandomLoop = new Button();
		lblSpeedWarning = new JLabel();
		jpConfig = new JPanel();
		btnSave = new JButton();
		btnLoad = new JButton();

		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		try {
			jLabel1.setIcon(new ImageIcon(new URL("http://betterpvp.net/osFighter/osFighter-header.png")));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // NOI18N
		getContentPane().add(lblLogo);
		lblLogo.setBounds(140, 0, 450, 110);

		buttonStart.setFont(new Font("Trebuchet MS", 3, 36)); // NOI18N
		buttonStart.setText("Start Script");
		getContentPane().add(buttonStart);
		buttonStart.setBounds(0, 510, 730, 60);

		pnlMain.setBorder(BorderFactory.createEtchedBorder());

		jpMain.setLayout(null);

		pnlMonsterSelection.setBorder(BorderFactory.createEtchedBorder());
		pnlMonsterSelection.setLayout(null);

		lblSelectTargets.setFont(new Font("Tahoma", 1, 18)); // NOI18N
		lblSelectTargets.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTargets.setText("Select Targets");
		pnlMonsterSelection.add(lblSelectTargets);
		lblSelectTargets.setBounds(10, 10, 300, 40);


		jScrollPane1.setViewportView(listNearbyTargets);

		pnlMonsterSelection.add(jScrollPane1);
		jScrollPane1.setBounds(10, 60, 300, 190);

		lblORFilter.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblORFilter.setHorizontalAlignment(SwingConstants.CENTER);
		lblORFilter.setText("OR");
		pnlMonsterSelection.add(lblORFilter);
		lblORFilter.setBounds(10, 280, 300, 30);

		lblTargetName.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		lblTargetName.setText("Target Name:");
		pnlMonsterSelection.add(lblTargetName);
		lblTargetName.setBounds(10, 310, 80, 30);
		pnlMonsterSelection.add(txtTargetName);
		txtTargetName.setBounds(90, 310, 220, 30);

		btnRefresh.setText("Refresh");
		pnlMonsterSelection.add(btnRefresh);
		btnRefresh.setBounds(230, 250, 80, 30);
		btnRefresh.addActionListener(e -> loadNPCs());

		jpMain.add(pnlMonsterSelection);
		pnlMonsterSelection.setBounds(10, 10, 320, 350);

		pnlMonsterList.setBorder(BorderFactory.createEtchedBorder());
		pnlMonsterList.setLayout(null);

		lblTargetList.setFont(new Font("Tahoma", 1, 18)); // NOI18N
		lblTargetList.setHorizontalAlignment(SwingConstants.CENTER);
		lblTargetList.setText("Current Targets");
		pnlMonsterList.add(lblTargetList);
		lblTargetList.setBounds(10, 10, 300, 40);


		jScrollPane2.setViewportView(listCurrentTargets);
		setPreferredSize(new Dimension(745, 450));
		pnlMonsterList.add(jScrollPane2);
		jScrollPane2.setBounds(10, 60, 300, 190);

		btnClearCurrent.setText("Clear");
		pnlMonsterList.add(btnClearCurrent);
		btnClearCurrent.setBounds(230, 250, 80, 30);

		jpMain.add(pnlMonsterList);
		pnlMonsterList.setBounds(390, 10, 320, 350);

		btnRemove.setText("<<");
		btnRemove.setToolTipText("Remove an item from the Current Targets list");
		jpMain.add(btnRemove);
		btnRemove.setBounds(330, 210, 60, 40);
		btnRemove.addActionListener(e -> removeTarget());

		btnAdd.setText(">>");
		btnAdd.setToolTipText("Add an item to the Current Targets list");
		jpMain.add(btnAdd);
		btnAdd.setBounds(329, 80, 60, 40);
		btnAdd.addActionListener(e -> addTarget());

		pnlMain.addTab("Main", jpMain);

		pnlFoodDrinks.setLayout(null);

		pnlFood.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pnlFood.setEnabled(false);
		pnlFood.setName("Food"); // NOI18N
		pnlFood.setLayout(null);

		lblEatBelow.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblEatBelow.setText("Eat below");
		pnlFood.add(lblEatBelow);
		lblEatBelow.setBounds(20, 20, 70, 30);

		spinnerEatHealth.setModel(new SpinnerNumberModel(25, 2, 99, 1));
		pnlFood.add(spinnerEatHealth);
		spinnerEatHealth.setBounds(100, 20, 50, 30);

		lblEatBelowHealth.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblEatBelowHealth.setText("health");
		pnlFood.add(lblEatBelowHealth);
		lblEatBelowHealth.setBounds(160, 20, 120, 30);

		lblEatBelowDeviation.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblEatBelowDeviation.setText("Deviation");
		pnlFood.add(lblEatBelowDeviation);
		lblEatBelowDeviation.setBounds(20, 70, 80, 30);

		spinnerEatDeviation.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerEatDeviation.setToolTipText("Please take monster max hit into consideration");
		pnlFood.add(spinnerEatDeviation);
		spinnerEatDeviation.setBounds(100, 70, 50, 30);

		lblFoodToEat.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblFoodToEat.setText("Food to Eat");
		pnlFood.add(lblFoodToEat);
		lblFoodToEat.setBounds(20, 120, 90, 30);

		txtFoodToEat.setToolTipText(
				"Ensure you spell the name correctly, if you do not specify a food, the script will eat anything edible in the inventory");

		pnlFood.add(txtFoodToEat);
		txtFoodToEat.setBounds(110, 120, 170, 30);

		pnlFoodDrinks.add(pnlFood);
		pnlFood.setBounds(10, 30, 340, 170);

		pnlDrinks.setBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pnlDrinks.setEnabled(false);
		pnlDrinks.setName("Potions"); // NOI18N
		pnlDrinks.setLayout(null);

		chkAntiposionPotion.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		chkAntiposionPotion.setText("Antipoison");
		chkAntiposionPotion.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDrinks.add(chkAntiposionPotion);
		chkAntiposionPotion.setBounds(80, 290, 83, 30);

		chkAntifirePotion.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		chkAntifirePotion.setText("Antifire");
		chkAntifirePotion.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDrinks.add(chkAntifirePotion);
		chkAntifirePotion.setBounds(10, 290, 65, 30);

		chkMagicPotion.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		chkMagicPotion.setText("Magic");
		chkMagicPotion.setIconTextGap(2);
		chkMagicPotion.setMaximumSize(new Dimension(67, 23));
		chkMagicPotion.setMinimumSize(new Dimension(67, 23));
		chkMagicPotion.setPreferredSize(new Dimension(67, 23));
		pnlDrinks.add(chkMagicPotion);
		chkMagicPotion.setBounds(30, 230, 70, 23);

		chkRangingPotion.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		chkRangingPotion.setText("Ranging");
		chkRangingPotion.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDrinks.add(chkRangingPotion);
		chkRangingPotion.setBounds(30, 170, 70, 23);

		chkDefencePotion.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		chkDefencePotion.setText("Defence");

		pnlDrinks.add(chkDefencePotion);
		chkDefencePotion.setBounds(240, 80, 71, 23);

		chkStrengthPotion.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		chkStrengthPotion.setText("Strength");

		pnlDrinks.add(chkStrengthPotion);
		chkStrengthPotion.setBounds(130, 80, 75, 23);

		chkAttackPotion.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		chkAttackPotion.setText("Attack");
		chkAttackPotion.setHorizontalTextPosition(SwingConstants.RIGHT);
		chkAttackPotion.setMaximumSize(new Dimension(67, 23));
		pnlDrinks.add(chkAttackPotion);
		chkAttackPotion.setBounds(30, 80, 70, 23);

		lblExplainPotions.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		lblExplainPotions.setHorizontalAlignment(SwingConstants.CENTER);
		lblExplainPotions.setText(
				"<html>\n<center>The script will automatically drink the best potion of\n<br>each type available");
		pnlDrinks.add(lblExplainPotions);
		lblExplainPotions.setBounds(0, -10, 340, 70);

		lblMeleePotions.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblMeleePotions.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeleePotions.setText("Melee");
		pnlDrinks.add(lblMeleePotions);
		lblMeleePotions.setBounds(4, 60, 330, 17);

		chkCombatPotion.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		chkCombatPotion.setText("Combat");
		chkCombatPotion.setHorizontalTextPosition(SwingConstants.RIGHT);
		pnlDrinks.add(chkCombatPotion);
		chkCombatPotion.setBounds(30, 110, 70, 23);

		lblOtherPotions.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblOtherPotions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtherPotions.setText("Other");
		pnlDrinks.add(lblOtherPotions);
		lblOtherPotions.setBounds(0, 260, 340, 30);

		lblMagicPotions.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblMagicPotions.setHorizontalAlignment(SwingConstants.CENTER);
		lblMagicPotions.setText("Magic");
		pnlDrinks.add(lblMagicPotions);
		lblMagicPotions.setBounds(0, 200, 340, 30);

		lblRangingPotions.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblRangingPotions.setHorizontalAlignment(SwingConstants.CENTER);
		lblRangingPotions.setText("Range");
		pnlDrinks.add(lblRangingPotions);
		lblRangingPotions.setBounds(0, 140, 340, 30);

		chkAntivenomPotion.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		chkAntivenomPotion.setText("Anti-venom");
		pnlDrinks.add(chkAntivenomPotion);
		chkAntivenomPotion.setBounds(170, 290, 89, 30);

		chkAntidotePotion.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		chkAntidotePotion.setText("Antidote");
		pnlDrinks.add(chkAntidotePotion);
		chkAntidotePotion.setBounds(260, 290, 73, 30);

		pnlFoodDrinks.add(pnlDrinks);
		pnlDrinks.setBounds(370, 30, 340, 330);

		chkDrinksEnabled.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		chkDrinksEnabled.setText("Drink Potions?");

		pnlFoodDrinks.add(chkDrinksEnabled);
		chkDrinksEnabled.setBounds(370, 0, 340, 30);

		chkOtherHealing.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		chkOtherHealing.setText("Other Healing");
		pnlFoodDrinks.add(chkOtherHealing);
		chkOtherHealing.setBounds(10, 210, 340, 30);

		chkFoodEnabled.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		chkFoodEnabled.setText("Eat Food?");
		pnlFoodDrinks.add(chkFoodEnabled);
		chkFoodEnabled.setBounds(10, 0, 340, 30);

		pnlFood1.setBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pnlFood1.setEnabled(false);
		pnlFood1.setName("Food"); // NOI18N
		pnlFood1.setLayout(null);

		chkUseSGS.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		chkUseSGS.setText("SGS");
		pnlFood1.add(chkUseSGS);
		chkUseSGS.setBounds(10, 60, 60, 30);

		chkUseGuthans.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		chkUseGuthans.setText("Guthans");
		pnlFood1.add(chkUseGuthans);
		chkUseGuthans.setBounds(10, 20, 90, 30);

		lblGuthansUseBelow.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		lblGuthansUseBelow.setText("Use below");
		pnlFood1.add(lblGuthansUseBelow);
		lblGuthansUseBelow.setBounds(120, 20, 70, 30);

		spinnerGuthansHealth.setModel(new SpinnerNumberModel(35, 2, 99, 1));
		pnlFood1.add(spinnerGuthansHealth);
		spinnerGuthansHealth.setBounds(190, 20, 50, 30);

		lblGuthansBelowHealth.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		lblGuthansBelowHealth.setText("health");
		pnlFood1.add(lblGuthansBelowHealth);
		lblGuthansBelowHealth.setBounds(250, 20, 50, 30);

		pnlFoodDrinks.add(pnlFood1);
		pnlFood1.setBounds(10, 240, 340, 120);

		pnlMain.addTab("Food / Drinks", pnlFoodDrinks);

		pnlLoot.setLayout(null);

		pnlAddItems.setBorder(BorderFactory.createEtchedBorder());
		pnlAddItems.setLayout(null);

		lblSelectTargets2.setFont(new Font("Tahoma", 1, 18)); // NOI18N
		lblSelectTargets2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTargets2.setText("Add Items");
		pnlAddItems.add(lblSelectTargets2);
		lblSelectTargets2.setBounds(0, 10, 320, 40);

		jLabel1.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel1.setText("Item Name:");
		pnlAddItems.add(jLabel1);
		jLabel1.setBounds(10, 90, 70, 30);
		pnlAddItems.add(txtItemName);
		txtItemName.setBounds(90, 90, 220, 30);

		lblLootother.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblLootother.setHorizontalAlignment(SwingConstants.CENTER);
		lblLootother.setText("Other");
		pnlAddItems.add(lblLootother);
		lblLootother.setBounds(0, 230, 320, 20);

		pnlLootConditions.setBorder(
				BorderFactory.createBevelBorder(BevelBorder.RAISED));
		pnlLootConditions.setLayout(null);

		rbLootNone.setSelected(true);
		rbLootNone.setText("None");
		pnlLootConditions.add(rbLootNone);
		rbLootNone.setBounds(10, 10, 60, 23);

		rbLootNoted.setText("Noted");

		pnlLootConditions.add(rbLootNoted);
		rbLootNoted.setBounds(70, 10, 60, 23);

		rbLootLowAlch.setText("Low Alch");
		rbLootLowAlch.setMaximumSize(new Dimension(66, 23));
		rbLootLowAlch.setMinimumSize(new Dimension(66, 23));
		rbLootLowAlch.setPreferredSize(new Dimension(66, 23));
		pnlLootConditions.add(rbLootLowAlch);
		rbLootLowAlch.setBounds(130, 10, 80, 23);

		rbLootHighAlch.setText("High Alch");
		pnlLootConditions.add(rbLootHighAlch);
		rbLootHighAlch.setBounds(210, 10, 80, 23);

		pnlAddItems.add(pnlLootConditions);
		pnlLootConditions.setBounds(10, 180, 300, 40);

		lblLootConditions.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblLootConditions.setHorizontalAlignment(SwingConstants.CENTER);
		lblLootConditions.setText("Loot Conditions");
		pnlAddItems.add(lblLootConditions);
		lblLootConditions.setBounds(0, 150, 320, 20);

		chkItemReplaceable
		.setText("<html><center>Replace if higher value item is found and inventory is full");
		pnlAddItems.add(chkItemReplaceable);
		chkItemReplaceable.setBounds(70, 260, 190, 60);

		pnlLoot.add(pnlAddItems);
		pnlAddItems.setBounds(10, 10, 320, 350);

		pnlItemList.setBorder(BorderFactory.createEtchedBorder());
		pnlItemList.setLayout(null);

		lblCurrentItems.setFont(new Font("Tahoma", 1, 18)); // NOI18N
		lblCurrentItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentItems.setText("Current Items");
		pnlItemList.add(lblCurrentItems);
		lblCurrentItems.setBounds(10, 10, 300, 40);


		jScrollPane6.setViewportView(listLootingItems);

		pnlItemList.add(jScrollPane6);
		jScrollPane6.setBounds(10, 60, 300, 190);

		btnClearCurrent2.setText("Clear");
		pnlItemList.add(btnClearCurrent2);
		btnClearCurrent2.setBounds(230, 250, 80, 30);

		pnlLoot.add(pnlItemList);
		pnlItemList.setBounds(390, 10, 320, 350);

		btnLootRemove.setText("<<");
		btnLootRemove.setToolTipText("Remove an item from the Current Targets list");
		pnlLoot.add(btnLootRemove);
		btnLootRemove.setBounds(330, 210, 60, 40);

		btnLootAdd.setText(">>");
		btnLootAdd.setToolTipText("Add an item to the Current Targets list");
		pnlLoot.add(btnLootAdd);
		btnLootAdd.setBounds(329, 80, 60, 40);

		pnlMain.addTab("Looting", pnlLoot);

		pnlBank.setLayout(null);

		chkBanking.setText("Banking");
		pnlBank.add(chkBanking);
		chkBanking.setBounds(10, 10, 63, 23);

		pnlSubBanking.setBorder(BorderFactory.createEtchedBorder());
		pnlSubBanking.setEnabled(false);
		pnlSubBanking.setLayout(null);

		chkDepositInventory.setText("Deposit inventory");
		chkDepositInventory.setToolTipText("Deposit your entire inventory before withdrawing new items");
		pnlSubBanking.add(chkDepositInventory);
		chkDepositInventory.setBounds(10, 20, 120, 23);

		lblBankWithdrawAmount.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblBankWithdrawAmount.setText("Withdraw");
		pnlSubBanking.add(lblBankWithdrawAmount);
		lblBankWithdrawAmount.setBounds(20, 60, 80, 30);

		jSpinner2.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		jSpinner2.setModel(new SpinnerNumberModel(1, 1, null, 1));
		pnlSubBanking.add(jSpinner2);
		jSpinner2.setBounds(100, 60, 50, 30);
		pnlSubBanking.add(txtItemToWithdraw);
		txtItemToWithdraw.setBounds(160, 60, 190, 30);

		jList1.setModel(new AbstractListModel<String>() {
			String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

			public int getSize() {
				return strings.length;
			}

			public String getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane5.setViewportView(jList1);

		pnlSubBanking.add(jScrollPane5);
		jScrollPane5.setBounds(360, 40, 330, 230);

		btnAddBankItem.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		btnAddBankItem.setText("Add Item");

		pnlSubBanking.add(btnAddBankItem);
		btnAddBankItem.setBounds(120, 110, 120, 40);

		btnClearBankList.setText("Clear");
		pnlSubBanking.add(btnClearBankList);
		btnClearBankList.setBounds(520, 270, 170, 23);

		btnRemoveSelectedBankItem.setText("Remove Selected");
		pnlSubBanking.add(btnRemoveSelectedBankItem);
		btnRemoveSelectedBankItem.setBounds(360, 270, 160, 23);

		lblWithdrawList.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblWithdrawList.setHorizontalAlignment(SwingConstants.CENTER);
		lblWithdrawList.setText("Withdraw List");
		pnlSubBanking.add(lblWithdrawList);
		lblWithdrawList.setBounds(360, 4, 330, 30);

		pnlBank.add(pnlSubBanking);
		pnlSubBanking.setBounds(10, 40, 700, 320);

		pnlMain.addTab("Banking", pnlBank);

		pnlPrayer.setLayout(null);

		jCheckBox1.setText("Use Prayer?");
		pnlPrayer.add(jCheckBox1);
		jCheckBox1.setBounds(10, 10, 100, 30);

		pnlSubPrayer.setBorder(BorderFactory.createEtchedBorder());
		pnlSubPrayer.setEnabled(false);
		pnlSubPrayer.setLayout(null);

		lblPrayerDrinkBelow.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblPrayerDrinkBelow.setText("Drink below");
		pnlSubPrayer.add(lblPrayerDrinkBelow);
		lblPrayerDrinkBelow.setBounds(20, 30, 90, 30);

		spinnerMinPrayer.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		spinnerMinPrayer.setModel(new SpinnerNumberModel(15, 1, 99, 1));
		pnlSubPrayer.add(spinnerMinPrayer);
		spinnerMinPrayer.setBounds(110, 30, 50, 30);

		lblPrayerPoints.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblPrayerPoints.setText("prayer points");
		pnlSubPrayer.add(lblPrayerPoints);
		lblPrayerPoints.setBounds(170, 30, 110, 30);

		chkBankNoPrayerPots.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		chkBankNoPrayerPots.setText("Bank when out of prayer / restore potions");
		pnlSubPrayer.add(chkBankNoPrayerPots);
		chkBankNoPrayerPots.setBounds(20, 70, 320, 25);

		jScrollPane4.setBorder(BorderFactory.createEtchedBorder());

		tablePrayers.setModel(new DefaultTableModel(
				new Object[][] {{null, null, null, null, null}, {null, null, null, null, null},
					{null, null, null, null, null}, {null, null, null, null, null},
					{null, null, null, null, null}, {null, null, null, null, null}},
				new String[] {"", "", "", "", ""}) {
			boolean[] canEdit = new boolean[] {false, false, false, false, false};

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane4.setViewportView(tablePrayers);
		if (tablePrayers.getColumnModel().getColumnCount() > 0) {
			tablePrayers.getColumnModel().getColumn(0).setResizable(false);
			tablePrayers.getColumnModel().getColumn(1).setResizable(false);
			tablePrayers.getColumnModel().getColumn(2).setResizable(false);
			tablePrayers.getColumnModel().getColumn(3).setResizable(false);
			tablePrayers.getColumnModel().getColumn(4).setResizable(false);
		}

		pnlSubPrayer.add(jScrollPane4);
		jScrollPane4.setBounds(450, 20, 210, 270);

		pnlPrayer.add(pnlSubPrayer);
		pnlSubPrayer.setBounds(10, 40, 700, 320);

		pnlMain.addTab("Prayer", pnlPrayer);

		jpOther.setLayout(null);

		chkUseCannon.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		chkUseCannon.setText("Use Dwarf Cannon?");
		jpOther.add(chkUseCannon);
		chkUseCannon.setBounds(10, 20, 170, 23);

		chkSafeSpot.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		chkSafeSpot.setText("Safe Spot?");
		jpOther.add(chkSafeSpot);
		chkSafeSpot.setBounds(200, 20, 100, 23);

		pnlMain.addTab("Other", jpOther);

		jpScheduler.setLayout(null);


		jScrollPane3.setViewportView(listTasks);

		jpScheduler.add(jScrollPane3);
		jScrollPane3.setBounds(10, 10, 700, 160);

		btnAddTask2.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		btnAddTask2.setText("Add Task");

		jpScheduler.add(btnAddTask2);
		btnAddTask2.setBounds(330, 270, 100, 30);

		lblTaskUntilLevel.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblTaskUntilLevel.setText("until level");
		jpScheduler.add(lblTaskUntilLevel);
		lblTaskUntilLevel.setBounds(200, 220, 70, 30);

		lblTaskTrain.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblTaskTrain.setText("Train");
		jpScheduler.add(lblTaskTrain);
		lblTaskTrain.setBounds(10, 220, 40, 30);

		cmbTaskAttackStyle.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		cmbTaskAttackStyle.setModel(new DefaultComboBoxModel<>(
				new String[] {"Attack", "Strength", "Defence", "Range"}));

		jpScheduler.add(cmbTaskAttackStyle);
		cmbTaskAttackStyle.setBounds(60, 220, 130, 30);

		jSpinner1.setModel(new SpinnerNumberModel(50, 2, 99, 1));
		jpScheduler.add(jSpinner1);
		jSpinner1.setBounds(270, 220, 40, 30);

		btnAddTask1.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		btnAddTask1.setText("Add Task");

		jpScheduler.add(btnAddTask1);
		btnAddTask1.setBounds(330, 220, 100, 30);

		btnRemoveSelectedTask.setFont(new Font("Tahoma", 0, 14)); // NOI18N
		btnRemoveSelectedTask.setText("Remove Selected");

		jpScheduler.add(btnRemoveSelectedTask);
		btnRemoveSelectedTask.setBounds(10, 170, 700, 30);

		lblTaskEquip.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblTaskEquip.setText("Equip");
		jpScheduler.add(lblTaskEquip);
		lblTaskEquip.setBounds(10, 270, 50, 30);
		jpScheduler.add(txtTaskItemToEquip);
		txtTaskItemToEquip.setBounds(60, 270, 130, 30);

		pnlMain.addTab("Scheduler ", jpScheduler);

		jpAntiban.setLayout(null);

		jtAntiban.setBorder(BorderFactory.createEtchedBorder());
		jtAntiban.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] {"Anti Ban", "Enabled"}) {
			Class[] types = new Class[] {java.lang.String.class, java.lang.Boolean.class};

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		jtAntiban.setShowHorizontalLines(false);
		jpAntiban.add(jtAntiban);
		jtAntiban.setBounds(10, 60, 700, 190);
		jtAntiban.getAccessibleContext().setAccessibleParent(jpAntiban);

		jLabel2.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		jLabel2.setText(
				"NOTE: These may or may not have an impact on whether your account is banned or not. Bot at your own risk.");
		jpAntiban.add(jLabel2);
		jLabel2.setBounds(20, 20, 680, 30);

		pnlMain.addTab("Antiban", jpAntiban);

		jpSpeed.setLayout(null);

		lblMinSpeed.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblMinSpeed.setText("Min Loop Speed:");
		jpSpeed.add(lblMinSpeed);
		lblMinSpeed.setBounds(10, 40, 120, 50);

		jsbMinSpeed.setMaximum(1000);
		jsbMinSpeed.setMinimum(1);
		jsbMinSpeed.setOrientation(JScrollBar.HORIZONTAL);
		jsbMinSpeed.setValue(150);

		jpSpeed.add(jsbMinSpeed);
		jsbMinSpeed.setBounds(140, 50, 180, 30);

		lblCurrentMinSpeed.setAlignment(Label.CENTER);
		lblCurrentMinSpeed.setText(jsbMinSpeed.getValue() + "");
		jpSpeed.add(lblCurrentMinSpeed);
		lblCurrentMinSpeed.setBounds(140, 30, 180, 20);
		lblCurrentMinSpeed.getAccessibleContext().setAccessibleName(jsbMinSpeed.getValue() + "");

		lblMaxSpeed.setFont(new Font("Tahoma", 1, 14)); // NOI18N
		lblMaxSpeed.setText("Max Loop Speed:");
		jpSpeed.add(lblMaxSpeed);
		lblMaxSpeed.setBounds(10, 110, 120, 50);

		jsbMaxSpeed.setMaximum(1000);
		jsbMaxSpeed.setMinimum(1);
		jsbMaxSpeed.setOrientation(JScrollBar.HORIZONTAL);
		jsbMaxSpeed.setValue(250);

		jpSpeed.add(jsbMaxSpeed);
		jsbMaxSpeed.setBounds(140, 120, 180, 30);

		lblCurrentMaxSpeed.setAlignment(Label.CENTER);
		lblCurrentMaxSpeed.setText(jsbMaxSpeed.getValue() + "");
		jpSpeed.add(lblCurrentMaxSpeed);
		lblCurrentMaxSpeed.setBounds(140, 100, 180, 20);

		btnRandomLoop.setLabel("Randomize");
		jpSpeed.add(btnRandomLoop);
		btnRandomLoop.setBounds(140, 160, 180, 50);

		lblSpeedWarning.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		lblSpeedWarning.setText(
				"<html>Warning!!!<br><br>  Adjusting these speeds will affect the speed of the script! <br><br>  The lower the values, the faster the script will run.  <br>The higher the values, the slower the script will run.");
		jpSpeed.add(lblSpeedWarning);
		lblSpeedWarning.setBounds(370, 10, 330, 180);

		pnlMain.addTab("Speed", jpSpeed);

		jpConfig.setLayout(null);

		btnSave.setText("Save");
		jpConfig.add(btnSave);
		btnSave.setBounds(30, 20, 140, 70);

		btnLoad.setText("Load");
		jpConfig.add(btnLoad);
		btnLoad.setBounds(30, 130, 140, 70);

		pnlMain.addTab("Save / Load", jpConfig);

		getContentPane().add(pnlMain);
		pnlMain.setBounds(0, 110, 730, 400);

		pack();

		loadNPCs();
		this.setVisible(true);
	}


	private void loadNPCs() {

		nearbyModel.clear();



		instance.getNpcs().getAll().forEach(npc -> {
			FighterNPC n = new FighterNPC(npc.getName(), npc.getLevel());
			if(npc.isAttackable()) {
				if(!modelContains(currentModel, n)) {

					if(!modelContains(nearbyModel, n)) {
						nearbyModel.addElement(n);
					}
				}
			}
		});


	}


	private void addTarget() {
		if(txtTargetName.getText() != "") {
			String[] npcData = txtTargetName.getText().split(",");
			FighterNPC fNpc = new FighterNPC(npcData[0], Integer.valueOf(npcData[1].replaceAll(" ", "")));
			if(!currentModel.contains(fNpc)) {
				currentModel.addElement(fNpc);
			}

		}	else {
			if(!listNearbyTargets.isSelectionEmpty()) {
				FighterNPC fNpc = listNearbyTargets.getSelectedValue();
				if(!currentModel.contains(fNpc)) {
					currentModel.addElement(fNpc);
				}
			}
		}

		loadNPCs();
	}

	private void removeTarget() {
		if(!listCurrentTargets.isSelectionEmpty()) {
			FighterNPC fNpc = listCurrentTargets.getSelectedValue();
			if(currentModel.contains(fNpc)) {
				currentModel.removeElement(fNpc);
			}
		}

		loadNPCs();
	}

	private <T> boolean modelContains(DefaultListModel<T> model, T clazz) {
		for(int i = 0; i < model.size(); i++) {
			T x = model.get(i);
			if(clazz.equals(x)) {
				return true;
			}
		}

		return false;
	}


}
