import java.util.ArrayList;
import java.util.List;

//ObserverPatternTest-ის გამოყენებით, გვაქვს კლიენტის ტიპის სამი იმპლემენტაცია, რომლებიც რეგისტრაციისა და წაშლის დროს იღებენ მესიჯს ფაბლიშერისგან, რეგისტაციისა და წაშლის
//შესახებ. პაბლიშერი მესიჯს გამოიტანს მხოლოდ იმ კლიენტისთვის, რომლის რეგისტრაცია/წაშლა ხდება. მესიჯი დაიბეჭდება კონსოლში.
public class ObserverPatternHomeWork {

    public static void main(String[] args) {
        //declare Clients
        NewClientOne s1 = new NewClientOne();
        NewClientTwo s2 = new NewClientTwo();
        NewClientThee s3 = new NewClientThee();

        //register publisher  
        MessagePublisher p = new MessagePublisher();

        //attach Clients to  publisher
        p.attach(new Message("Client has been registered"), s1);
        p.attach(new Message("Client has been registered"), s2);
        p.attach(new Message("Client has been registered"), s3);

        //detach clients
        p.detach(new Message("Client has been removed"), s3);
        p.detach(new Message("Client has been removed"), s2);

        //attach clients again
        p.attach(new Message("Client has been registered"), s2);
        p.attach(new Message("Client has been registered"), s3);
    }

}

class Message {
    final String messageContent;

    public Message(String m) {
        this.messageContent = m;
    }

    public String getMessageContent() {
        return messageContent;
    }
}

interface Client {
    void update(Message m);
}

interface Subject {
    void attach(Message m, Client o);

    void detach(Message m, Client o);

}

class MessagePublisher implements Subject {

    private List<Client> clients = new ArrayList<>();

    @Override
    public void attach(Message m, Client o) {
        clients.add(o);
        o.update(m);
    }

    @Override
    public void detach(Message m, Client o) {
        clients.remove(o);
        o.update(m);
    }
}


// different implementation of subscribers type of Client
class NewClientOne implements Client {
    @Override
    public void update(Message m) {
        System.out.println("MessageSubscriberOne :: " + m.getMessageContent());
    }
}

class NewClientTwo implements Client {
    @Override
    public void update(Message m) {
        System.out.println("MessageSubscriberTwo :: " + m.getMessageContent());
    }
}

class NewClientThee implements Client {
    @Override
    public void update(Message m) {
        System.out.println("MessageSubscriberThree :: " + m.getMessageContent());
    }
}