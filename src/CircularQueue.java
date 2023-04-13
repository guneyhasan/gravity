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
        if(isFull())
            System.out.println("Queue Overflow");
        else
        {
            rear=(rear+1)%capacity;
            arr[rear]=obj;
        }
    }
    public Object dequeue()
    {
        if(isEmpty())
        {
            System.out.println("Queue is Empty");
            return null;
        }
        else
        {
            Object temp=arr[front];
            arr[front]=null;
            front=(front+1)%capacity;
            return temp;
        }

    }
    public int size()
    {
        if(arr[front]==null)
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
        if(arr[front]==null)
            return true;
        else
            return false;
    }

    public boolean isFull()
    {
        if (front==(rear + 1) % capacity && arr[front]!=null&&arr[rear]!=null)
            return true;
          else {
            return false;
        }
    }
    public Object peek()
    {
        if(!isEmpty())
            return arr[front];
        else {
            System.out.println("Queue is Empty");
            return null;
        }
    }
}
