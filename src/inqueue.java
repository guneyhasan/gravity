import java.util.Random;

public class inqueue
{
    Random r=new Random();
    CircularQueue c=new CircularQueue(15);
    public void Add()
    {
        int nextInt=r.nextInt(1,41);
        char next;
        if(nextInt<7)
            next='1';
        else if(nextInt<12)
            next='2';
        else if(nextInt<16)
            next='3';
        else if(nextInt<17)
            next='X';
        else if(nextInt<27)
            next='O';
        else if(nextInt<36)
            next=':';
        else
            next='e';
        c.enqueue(next);
    }
    public void Add(char ch)
    {
        c.enqueue(ch);
    }
    public char random()
    {
        int nextInt=r.nextInt(1,41);
        char next;
        if(nextInt<7)
            next='1';
        else if(nextInt<12)
            next='2';
        else if(nextInt<16)
            next='3';
        else if(nextInt<17)
            next='X';
        else if(nextInt<27)
            next='O';
        else if(nextInt<36)
            next=':';
        else
            next='e';
        return next;

    }
    public char pull()
    {
        return (char)c.dequeue();
    }
    public char peek()
    {
        return (char)c.peek();
    }
    public int size()
    {
        return c.size();
    }
    public boolean isFull()
    {
        return c.isFull();
    }













}
