package com.adampach.donkeykong.providers;

import com.adampach.donkeykong.abstraction.Provider;

import java.time.Instant;
import java.util.Random;

public class RandomIntervalGeneratorProvider implements Provider<Boolean>
{
    private final int startValue;
    private final int maxValue;
    private final Random random;
    private int actualValue;

    public RandomIntervalGeneratorProvider(
            int startValue,
            int maxValue,
            long seed)
    {
        this.startValue = startValue;
        this.maxValue = maxValue;
        random = new Random();
        actualValue = this.startValue;
    }

    public RandomIntervalGeneratorProvider(
            int startValue,
            int maxValue)
    {
        this(startValue, maxValue, Instant.now().toEpochMilli());
    }

    public RandomIntervalGeneratorProvider()
    {
        this(1, 1000);
    }

    @Override
    public Boolean provide() {
        int num = random.nextInt(this.maxValue - this.startValue) + this.startValue;
        if((num >= this.startValue && num <= this.actualValue)
                || (this.actualValue == this.maxValue))
        {
            this.actualValue = this.startValue;
            return true;
        }

        this.actualValue++;
        return false;
    }
}
