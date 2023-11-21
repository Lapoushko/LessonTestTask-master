package example.note;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteLogicTest {
    NoteLogic noteLogic = new NoteLogic();

    /**
     * Тест проверки комманды /add
     */
    @Test
    public void testAddCommand(){
        String response = noteLogic.handleMessage("/add Testing");
        Assert.assertEquals("Note added!",response);
        Assert.assertEquals("Your notes:\n" +
                        "1. Testing",
                noteLogic.handleMessage("/notes"));
    }

    /**
     * Тест проверки комманды /notes
     */
    @Test
    public void testNotesCommand(){
        noteLogic.handleMessage("/add Testing");
        Assert.assertEquals("Your notes:\n"+
                        "1. Testing",
                noteLogic.handleMessage("/notes"));
    }

    /**
     * Тест проверки комманды /edit
     */
    @Test
    public void testEditCommand(){
        noteLogic.handleMessage("/add Testing");
        String response = noteLogic.handleMessage("/edit 1 NewTesting");
        Assert.assertEquals("Note edited!", response);
        Assert.assertEquals("Your notes:\n"+
                        "1. NewTesting",
                noteLogic.handleMessage("/notes"));
    }

    /**
     * Тест проверки комманды /del
     */
    @Test
    public void testDelCommand(){
        noteLogic.handleMessage("/add Testing1");
        noteLogic.handleMessage("/add Testing2");
        String response = noteLogic.handleMessage("/del 1");
        Assert.assertEquals("Note deleted!",response);
        Assert.assertEquals("Your notes:\n"+
                        "1. Testing2",
                noteLogic.handleMessage("/notes"));
    }

}