import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

class GUIServer
{
Frame f;
TextField t;
List l;
Button b;
Socket s;
ServerSocket ss;
GUIServer()
{
f=new Frame("Server");
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
ss=new ServerSocket(2003);
s=ss.accept();
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
new GUIServer();
}
}