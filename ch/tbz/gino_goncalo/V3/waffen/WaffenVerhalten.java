package ch.tbz.gino_goncalo.V3.waffen;

/**
 * Strategy interface that encapsulates a weapon's behavior.
 * Implementations return a short description of the weapon being used.
 */
public interface WaffenVerhalten {
    /**
     * Returns a human-readable description of the weapon that is being used.
     */
    String verwendeteWaffe();
}


