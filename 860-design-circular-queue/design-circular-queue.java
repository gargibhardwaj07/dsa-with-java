class MyCircularQueue {
    int arr[];
    int size;
    int rear; // points to last inserted element
    int front; // points to first element (for deletion)

    public MyCircularQueue(int k) {
        arr = new int[k];
        size = k;
        rear = -1; // initially no element → rear = -1
        front = -1; // initially no element → front = -1
        
    }
    
    public boolean enQueue(int value) {
        if(isFull()){
            return false;
        }
        //agr phla elmnt add kr rhe h to
        if(front == -1){
           front = 0;
        }
        // move rear forward in circular way
       rear = (rear+1) % size;
      arr[rear]  = value; //hum rear s value jod rhe h or ++ kr rhe h
      return true;
        
    }
    
    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }
        int ans = arr[front];

        //last elmnt dlt
        if(rear == front){
              // only one element was present → now empty
            rear = front = -1;
        }else{
         // move front forward in circular way
        front = (front+1)%size;
        }
         return true;
    }
    
  public int Front() {
    if (isEmpty()) return -1;  
    return arr[front];
}

public int Rear() {
    if (isEmpty()) return -1;  
    return arr[rear];
}

    
    public boolean isEmpty() {
        return rear == -1 && front == -1;
        
    }
    
    public boolean isFull() {
        //agr last elmnt +1 hmara 1 elmnt front jara h to full h
        if((rear+1) % size == front){
            return true;
        }
        return false;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */