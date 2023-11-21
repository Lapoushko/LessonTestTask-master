package example.bot;

import org.junit.Assert;
import org.junit.Test;
public class BotLogicTest {
    User user = new User(1L);

    /**
     * Тест команды /notify
     * @throws InterruptedException исключение прерванного потока
     */
    @Test
    public void testNotification() throws InterruptedException{
        FakeBot fakeBot = new FakeBot();
        BotLogic botLogic = new BotLogic(fakeBot);

        botLogic.processCommand(user,"/notify");
        Assert.assertEquals("Введите текст напоминания",
                fakeBot.getMessages().get(0));

        botLogic.processCommand(user,"Тестирование напоминания");
        Assert.assertEquals("Через сколько секунд напомнить?",
                fakeBot.getMessages().get(1));

        botLogic.processCommand(user,"1");
        Assert.assertEquals("Напоминание установлено",
                fakeBot.getMessages().get(2));

        Assert.assertEquals(3, fakeBot.getMessages().size());
        Thread.sleep(1010);
        Assert.assertEquals("Сработало напоминание: 'Тестирование напоминания'",
                fakeBot.getMessages().get(3));
    }

    /**
     * Тест на квиз по математике
     * Тестирование распознования правильного ответа
     */
    @Test
    public void testMathQuizAnswerCorrect(){
        FakeBot fakeBot = new FakeBot();
        BotLogic botLogic = new BotLogic(fakeBot);

        botLogic.processCommand(user,"/test");
        Assert.assertEquals("Вычислите степень: 10^2",
                fakeBot.getMessages().get(0));

        botLogic.processCommand(user,"100");
        Assert.assertEquals("Правильный ответ!",
                fakeBot.getMessages().get(1));

        Assert.assertEquals("Сколько будет 2 + 2 * 2",
                fakeBot.getMessages().get(2));

        botLogic.processCommand(user,"6");
        Assert.assertEquals("Правильный ответ!",
                fakeBot.getMessages().get(3));
    }

    /**
     * Тест на квиз по математике
     * Тестирование распознования неправильного ответа
     */
    @Test
    public void testMathQuizAnswerWrong(){
        FakeBot fakeBot = new FakeBot();
        BotLogic botLogic = new BotLogic(fakeBot);

        botLogic.processCommand(user,"/test");
        Assert.assertEquals("Вычислите степень: 10^2",
                fakeBot.getMessages().get(0));

        botLogic.processCommand(user,"102");
        Assert.assertEquals("Вы ошиблись, верный ответ: 100",
                fakeBot.getMessages().get(1));

        Assert.assertEquals("Сколько будет 2 + 2 * 2",
                fakeBot.getMessages().get(2));

        botLogic.processCommand(user,"8");
        Assert.assertEquals("Вы ошиблись, верный ответ: 6",
                fakeBot.getMessages().get(3));
    }

    /**
     * Тест на команду /repeat
     */
    @Test
    public void testRepeat(){
        FakeBot fakeBot = new FakeBot();
        BotLogic botLogic = new BotLogic(fakeBot);

        botLogic.processCommand(user,"/repeat");
        Assert.assertEquals("Нет вопросов для повторения",
                fakeBot.getMessages().get(0));

        botLogic.processCommand(user,"/test");
        botLogic.processCommand(user,"102");
        botLogic.processCommand(user,"/repeat");
        Assert.assertEquals("Вычислите степень: 10^2",
                fakeBot.getMessages().get(4));
    }
}