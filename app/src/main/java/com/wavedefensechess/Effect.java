package com.wavedefensechess;

public interface Effect
{
    EffectActivationTime getActivationTime();
    void activateEffect(Position currentPosition, Position targetPosition, Board board);
}
