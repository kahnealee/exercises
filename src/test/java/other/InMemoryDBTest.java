package other;

import org.junit.Assert;
import org.junit.Test;

public class InMemoryDBTest {
    InMemoryDB db = InMemoryDB.getInstance();

    @Test
    public void testInMemoryDB1() {
        db.init();

        db.set("a", "10");
        Assert.assertEquals("10", db.get("a"));

        db.delete("a");
        Assert.assertNull(db.get("a"));

        db.end();
    }

    @Test
    public void testInMemoryDB2() {
        db.init();

        db.set("a", "10");
        db.set("b", "10");
        Assert.assertEquals(2, db.count("10"));
        Assert.assertEquals(0, db.count("20"));

        db.delete("a");
        Assert.assertEquals(1, db.count("10"));

        db.set("b", "30");
        Assert.assertEquals(0, db.count("10"));

        db.end();
    }

    @Test
    public void testInMemoryDB3() {
        db.init();

        db.begin();
        db.set("a", "10");
        Assert.assertEquals("10", db.get("a"));

        db.begin();
        db.set("a", "20");
        Assert.assertEquals("20", db.get("a"));

        db.rollback();
        Assert.assertEquals("10", db.get("a"));

        db.rollback();
        Assert.assertNull(db.get("a"));

        db.end();
    }

    @Test
    public void testInMemoryDB4() {
        db.init();

        db.begin();
        db.set("a", "30");
        db.begin();
        db.set("a", "40");
        db.commit();
        Assert.assertEquals("40", db.get("a"));

        Assert.assertEquals("NO TRANSACTION", db.rollback());

        db.end();
    }

    @Test
    public void testInMemoryDB5() {
        db.init();

        db.set("a", "50");
        db.begin();
        Assert.assertEquals("50", db.get("a"));
        db.set("a", "60");

        db.begin();
        db.delete("a");
        Assert.assertNull(db.get("a"));

        db.rollback();
        Assert.assertEquals("60", db.get("a"));

        db.commit();
        Assert.assertEquals("60", db.get("a"));

        db.end();
    }

    @Test
    public void testInMemoryDB6() {
        db.init();

        db.set("a", "10");
        db.begin();
        Assert.assertEquals(1, db.count("10"));

        db.begin();
        db.delete("a");
        Assert.assertEquals(0, db.count("10"));

        db.rollback();
        Assert.assertEquals(1, db.count("10"));

        db.end();
    }




    @Test
    public void testInMemoryDB7() {
        db.init();

        db.begin();
        db.set("a", "10");
        db.set("a", "20");
        db.set("a", "30");


        db.begin();
        Assert.assertEquals("30", db.get("a"));

        db.begin();
        db.set("a", "20");
        db.delete("a");
        db.set("a", "30");
        db.set("a", "40");
        Assert.assertEquals("40", db.get("a"));

        db.rollback();
        Assert.assertEquals("30", db.get("a"));

        db.rollback();
        Assert.assertEquals("30", db.get("a"));

        db.end();
    }
}