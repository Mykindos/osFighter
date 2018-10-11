package net.betterpvp.osFighter.data;

import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.script.Script;

import net.betterpvp.osFighter.states.banking.WithdrawData;
import net.betterpvp.osFighter.states.looting.LootedItem;

public class SessionData {
	

	private int maxLoopTime, minLoopTime;

	private Bank bank;


	private long lastAntiban;
	private long antibanGap;
	private List<FighterNPC> currentTargets = new ArrayList<>();

	// Food Variables
	private boolean eatFood, otherHealing;
	private boolean useGuthans, useSGS;
	private int useGuthansBelow;
	private int eatBelowHealth, eatBelowHealthDeviation;
	private String[] foodToEat;
	
	// Potion Variables
	private boolean drinkPotions;
	private boolean drinkAttackPotions, drinkStrengthPotions, drinkDefencePotions, drinkCombatPotions;
	private boolean drinkRangingPotions;
	private boolean drinkMagicPotions;
	private boolean drinkAntifirePotions, drinkAntipoisonPotions, drinkAntivenomPotions, drinkAntidotePotions;
	
	//Looting
	private List<LootedItem> lootableItems;
	
	
	//Banking
	private boolean isBanking;
	private boolean isDepositingInventory;
	private List<WithdrawData> withdrawData;
	private boolean shouldBankNow;
	
	//Prayer
	private boolean usePrayer;
	private int drinkBelowPrayer;
	private boolean bankWhenNoPrayerSupplies;
	
	//Other Settings
	private boolean isUsingCannon;
	private boolean isSafeSpotting;
	private String specWeapon;
	
	public SessionData(){
		
	}
	
	public boolean isUsingCannon() {
		return isUsingCannon;
	}

	public void setUsingCannon(boolean isUsingCannon) {
		this.isUsingCannon = isUsingCannon;
	}

	public boolean isSafeSpotting() {
		return isSafeSpotting;
	}

	public void setSafeSpotting(boolean isSafeSpotting) {
		this.isSafeSpotting = isSafeSpotting;
	}

	public boolean isUsePrayer() {
		return usePrayer;
	}

	public void setUsePrayer(boolean usePrayer) {
		this.usePrayer = usePrayer;
	}

	public int getDrinkBelowPrayer() {
		return drinkBelowPrayer;
	}

	public void setDrinkBelowPrayer(int drinkBelowPrayer) {
		this.drinkBelowPrayer = drinkBelowPrayer;
	}

	public boolean isBankWhenNoPrayerSupplies() {
		return bankWhenNoPrayerSupplies;
	}

	public void setBankWhenNoPrayerSupplies(boolean bankWhenNoPrayerSupplies) {
		this.bankWhenNoPrayerSupplies = bankWhenNoPrayerSupplies;
	}

	
	public boolean isDepositingInventory() {
		return isDepositingInventory;
	}

	public void setDepositingInventory(boolean isDepositingInventory) {
		this.isDepositingInventory = isDepositingInventory;
	}

	public boolean isBanking() {
		return isBanking;
	}
	
	public void setBanking(boolean banking) {
		this.isBanking = banking;
	}
	
	public List<WithdrawData> getWithdrawData(){
		return withdrawData;
	}
	
	public List<LootedItem> getLootableItems(){
		return lootableItems;
	}
	
	public boolean isDrinkPotions() {
		return drinkPotions;
	}

	public void setDrinkPotions(boolean drinkPotions) {
		this.drinkPotions = drinkPotions;
	}

	public boolean isDrinkAttackPotions() {
		return drinkAttackPotions;
	}

	public void setDrinkAttackPotions(boolean drinkAttackPotions) {
		this.drinkAttackPotions = drinkAttackPotions;
	}

	public boolean isDrinkStrengthPotions() {
		return drinkStrengthPotions;
	}

	public void setDrinkStrengthPotions(boolean drinkStrengthPotions) {
		this.drinkStrengthPotions = drinkStrengthPotions;
	}

	public boolean isDrinkDefencePotions() {
		return drinkDefencePotions;
	}

	public void setDrinkDefencePotions(boolean drinkDefencePotions) {
		this.drinkDefencePotions = drinkDefencePotions;
	}

	public boolean isDrinkCombatPotions() {
		return drinkCombatPotions;
	}

	public void setDrinkCombatPotions(boolean drinkCombatPotions) {
		this.drinkCombatPotions = drinkCombatPotions;
	}

	public boolean isDrinkRangingPotions() {
		return drinkRangingPotions;
	}

	public void setDrinkRangingPotions(boolean drinkRangingPotions) {
		this.drinkRangingPotions = drinkRangingPotions;
	}

	public boolean isDrinkMagicPotions() {
		return drinkMagicPotions;
	}

	public void setDrinkMagicPotions(boolean drinkMagicPotions) {
		this.drinkMagicPotions = drinkMagicPotions;
	}

	public boolean isDrinkAntifirePotions() {
		return drinkAntifirePotions;
	}

	public void setDrinkAntifirePotions(boolean drinkAntifirePotions) {
		this.drinkAntifirePotions = drinkAntifirePotions;
	}

	public boolean isDrinkAntipoisonPotions() {
		return drinkAntipoisonPotions;
	}

	public void setDrinkAntipoisonPotions(boolean drinkAntipoisonPotions) {
		this.drinkAntipoisonPotions = drinkAntipoisonPotions;
	}

	public boolean isDrinkAntivenomPotions() {
		return drinkAntivenomPotions;
	}

	public void setDrinkAntivenomPotions(boolean drinkAntivenomPotions) {
		this.drinkAntivenomPotions = drinkAntivenomPotions;
	}

	public boolean isDrinkAntidotePotions() {
		return drinkAntidotePotions;
	}

	public void setDrinkAntidotePotions(boolean drinkAntidotePotions) {
		this.drinkAntidotePotions = drinkAntidotePotions;
	}

	public boolean isOtherHealing() {
		return otherHealing;
	}

	public void setOtherHealing(boolean otherHealing) {
		this.otherHealing = otherHealing;
	}

	public boolean isUseGuthans() {
		return useGuthans;
	}

	public void setUseGuthans(boolean useGuthans) {
		this.useGuthans = useGuthans;
	}

	public boolean isUseSGS() {
		return useSGS;
	}

	public void setUseSGS(boolean useSGS) {
		this.useSGS = useSGS;
	}
	
	

	public int getUseGuthansBelow() {
		return useGuthansBelow;
	}

	public void setUseGuthansBelow(int useGuthansBelow) {
		this.useGuthansBelow = useGuthansBelow;
	}

	public String[] getFoodToEat() {
		return foodToEat;
	}

	public void setFoodToEat(String foodString) {
		this.foodToEat = foodString.replaceAll(" ", "").split(",");
	}

	public boolean isEatingFood() {
		return eatFood;
	}
	
	public void setEatingFood(boolean b) {
		this.eatFood = b;
	}
	
	public int getHealthToEatBelow() {
		return eatBelowHealth;
	}
	
	public void setHealthToEatBelow(int i) {
		this.eatBelowHealth = i;
	}
	
	public int getHealthDeviation() {
		return eatBelowHealthDeviation;
	}
	
	public void setHealthDeviation(int i) {
		this.eatBelowHealthDeviation = i;
	}
	
	
	
	
	public List<FighterNPC> getCurrentTargets(){
		return currentTargets;
	}
	
	public void setCurrentTargets(List<FighterNPC> targets) {
		this.currentTargets = targets;
	}
	
	public void setBank(Bank b){
		this.bank = b;
	}
	
	public Bank getBank(){
		return bank;
	}
	
	
	

	public void setMaxLoopTime(int i){
		this.maxLoopTime = i;
	}
	
	public void setMinLoopTime(int i){
		this.minLoopTime = i;
	}
	
	
	public int getMaxLoopTime(){
		return maxLoopTime;
	}
	
	public int getMinLoopTime(){
		return minLoopTime;
	}
	
	
	
	public void setLastAntiban(long l){
		this.lastAntiban = l;
	}
	
	public long getLastAntiban(){
		return lastAntiban;
	}
	
	public void setAntibanGap(){
		antibanGap = Script.random(240000, 1020000);
	}
	
	public long getAntibanGap(){
		return antibanGap;
	}
	
	
	public void setShouldBankNow(boolean b) {
		this.shouldBankNow = b;
	}
	
	public boolean shouldBankNow() {
		return shouldBankNow;
	}
	
	public void setSpecWeapon(String spec) {
		this.specWeapon = spec;
	}
	
	public String getSpecWeapon() {
		return specWeapon;
	}

	

}
