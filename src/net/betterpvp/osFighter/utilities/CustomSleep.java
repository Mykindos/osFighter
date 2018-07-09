package net.betterpvp.osFighter.utilities;

import java.util.function.BooleanSupplier;

import org.osbot.rs07.utility.ConditionalSleep;

public class CustomSleep extends ConditionalSleep{
	
	private BooleanSupplier condition;

	public CustomSleep(BooleanSupplier condition, int timeout) {
		super(timeout);
		this.condition = condition;
	}

	@Override
	public boolean condition() throws InterruptedException {
		return condition.getAsBoolean();
	}

}
