import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

class GUIClient
{
Frame f;
TextField t;
List l;
Button b;
Socket s;
GUIClient()
{
f=new Frame("Client");
t=new TextField();
l=new List();
b=new Button("Send");
b.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
String text=t.getText();
oos.writeObject(text);
l.add("Me:-"+text);
}
catch(Exception ex)
{
System.out.println(ex.getMessage());
}
}
});
f.add(t,BorderLayout.NORTH);
f.add(l);
f.add(b,BorderLayout.SOUTH);
f.setSize(300,300);
f.setVisible(true);
try
{
s=new Socket("localhost",2003);
while(true)
{
ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
String str=ois.readObject().toString();
l.add("Friend:-"+str);
}
}
catch(Exception e)
{
System.out.println(e.getMessage());
}
}
public static void main(String ar[])
{
new GUIClient();
}
}