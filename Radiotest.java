import org.junit.Test;
import static org.junit.Assert.*;

public class RadioTest {

    @Test
    public void testSwitchOnOff() {
        Radio radio = new Radio();

        assertFalse(radio.isON());
        radio.switchOnOff();
        assertTrue(radio.isON());


        radio.switchOnOff();
        assertFalse(radio.isON());
    }

    @Test
    public void testSwitchAMFM() {
        Radio radio = new Radio();

        assertTrue(radio.isAM());
        radio.switchAMFM();
        assertFalse(radio.isAM());
        assertEquals(87.9, radio.nextStation(), 0.01); 
        radio.switchAMFM();
        assertTrue(radio.isAM());
        assertEquals(530.0, radio.nextStation(), 0.01); 
    }

    @Test
    public void testNextStation() {
        Radio radio = new Radio();

        assertEquals(530.0, radio.nextStation(), 0.01);
        assertEquals(540.0, radio.nextStation(), 0.01);
        assertEquals(550.0, radio.nextStation(), 0.01);

        radio.switchAMFM();
        assertEquals(87.9, radio.nextStation(), 0.01);
        assertEquals(88.1, radio.nextStation(), 0.01);
        assertEquals(88.3, radio.nextStation(), 0.01);

        radio.switchAMFM();
        assertEquals(88.5, radio.nextStation(), 0.01);
        assertEquals(88.7, radio.nextStation(), 0.01);
        assertEquals(88.9, radio.nextStation(), 0.01);

        radio.switchAMFM();
        assertEquals(530.0, radio.nextStation(), 0.01);
        assertEquals(530.2, radio.nextStation(), 0.01);
        assertEquals(530.4, radio.nextStation(), 0.01);
    }

    @Test
    public void testSaveAndSelectStation() {
        Radio radio = new Radio();

        radio.switchOnOff();
        radio.saveStation(1, 100.5);
        assertEquals(100.5, radio.selectStation(1), 0.01);

        radio.saveStation(2, 95.3);
        assertEquals(95.3, radio.selectStation(2), 0.01);

        assertEquals(-1.0, radio.selectStation(3), 0.01);
    }
}

