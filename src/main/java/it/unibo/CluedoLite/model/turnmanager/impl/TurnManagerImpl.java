package it.unibo.CluedoLite.model.turnmanager.impl;

import java.util.*;

import it.unibo.CluedoLite.model.turnmanager.api.TurnManager;

/**
 * Implementation of the {@link TurnManager} interface.
 */
public class TurnManagerImpl implements TurnManager{

    private static final int MIN_PLAYERS=3;
    private static final int MAX_PLAYERS=6;
    private final List<Player> players;
    private int current_index;
/**
 * Constructs a TurnManager with the given list of players.
 * 
 * @param players the list of the players of the game
 * @throws IllegalArgumentException if the players list is null, has fewer than 3 or more than 6 players
 * 
 */
    public TurnManagerImpl(List<Player> players){
        if(players==null){
            throw new IllegalArgumentException("Il nummero di giocatori non può essere nullo");
        }

        if(players.size()<MIN_PLAYERS || players.size()>MAX_PLAYERS){
            throw new IllegalArgumentException("Il numero di giocatori deve essere compreso tra 3 e 6");
        }

        this.players=List.copyOf(players);
        this.current_index=0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getCurrentPlayer(){
        return this.players.get(this.current_index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player nextTurn(){
        this.current_index=(this.current_index+1)%this.players.size();
        return this.players.get(this.current_index);
    }
}
