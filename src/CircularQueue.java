public class CircularQueue
{
    public int front=0;
    public int rear=-1;
    private Object[] arr;
    private int capacity;

    public CircularQueue(int cap)
    {
        capacity=cap;
        arr=new Object[cap];
    }
    public void enqueue(Object obj)
    {
        if(rear==capacity-1)
            rear=0;
        if(!isFull())
            arr[++rear]=obj;
        else
            System.out.println("Queue Overflow");
    }
    public Object dequeue()
    {
        Object temp;
        if(!isEmpty())
        {
            temp=arr[front];
            arr[front++] = null;
        }
        else
        {
            System.out.println("Queue is Empty");
            return null;
        }

        if(front==capacity-1)
            front=0;
        return temp;
    }
    public int size()
    {
        if(rear==-1||arr[front]==null)
        {
            return 0;
        }
        else if(front<=rear)
            return  rear-front+1;
        else
            return capacity-(front-rear-1);
    }
    public boolean isEmpty()
    {
        if(size()==0)
            return true;
        else
            return false;
    }

    public boolean isFull()
    {
        if(size()==capacity)
            return true;
        else return false;
    }
    public Object peek()
    {
        if(!isEmpty())
            return arr[front];
        else
            return "Queue Empty";
    }
}
