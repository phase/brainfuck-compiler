package brainfuckcompiler.code.random;

import brainfuckcompiler.code.BrainfuckTools;

/**
 *
 * @author vrighter
 */
public class RandomNumberGenerator
{

    private BrainfuckTools t;
    private int randomh, randoml;

    /**
     *
     * @param t
     */
    public RandomNumberGenerator(BrainfuckTools t)
    {
        this.t = t;
        randomh = t.alloc();
        randoml = t.alloc();
    }

    /**
     *
     * @param x
     */
    public void generateRandomNumber(int x)
    {
        int[] temp = t.alloc(6);
        t.move(temp[0], randomh);
        t.move(temp[1], randoml);
        t.plus(temp[3], 7);
        t.loop();
        t.plus(temp[2], 11);
        t.deloop(temp[3]);
        t.loop(temp[2]);
        t.copy(randomh, temp[0], temp[3]);
        t.loop(temp[1]);
        t.inc(randomh);
        t.inc(temp[3]);
        t.inc(temp[4]);
        t.deloop(temp[1]);
        t.move(temp[1], temp[4]);
        t.loop(temp[3]);
        t.inc(randoml);
        t.loop();
        t.inc(temp[4]);
        t.inc(temp[5]);
        t.deloop(randoml);
        t.move(randoml, temp[5]);
        t.plus(1);
        t.loop(temp[4]);
        t.dec(temp[5]);
        t.celoop(temp[4]);
        t.move(randomh, temp[5]);
        t.deloop(temp[3]);
        t.deloop(temp[2]);
        t.plus(6);
        t.loop();
        t.plus(temp[3], 8);
        t.deloop(temp[2]);
        t.dec(temp[3]);
        t.loop();
        t.copy(randomh, temp[1], temp[2]);
        t.deloop(temp[3]);
        t.clear(temp[0]);
        t.set(temp[1], 5);
        t.loop();
        t.plus(temp[0], 5);
        t.deloop(temp[1]);
        t.loop(temp[0]);
        t.inc(randoml);
        t.loop();
        t.inc(temp[1]);
        t.inc(temp[2]);
        t.deloop(randoml);
        t.move(randoml, temp[2]);
        t.plus(1);
        t.loop(temp[1]);
        t.dec(temp[2]);
        t.celoop(temp[1]);
        t.move(randomh, temp[2]);
        t.deloop(temp[0]);
        t.plus(6);
        t.loop();
        t.plus(randomh, 9);
        t.deloop(temp[0]);
        t.copy(x, randomh, temp[0]);
        t.free(temp);
    }
}
