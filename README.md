# Teaching-HEIGVD-RES-2017-Labo-SMTP

This application allow the sending of prank messages where the apparent sender is one of the victims.

The user defines the possible prank message in the `data/messages/` folder. Each file is one message. The first line of the file is the message's subject.

The user also defines a list of victims in the `data/victims.txt` file. Each line is the email adress of a victim.

At execution, the user tells the program how many groups there should be. The victims are divided accordingly and randomly. For each group, one victim is chosen as the sender and the others are the recipients of the same random prank messages.

The should be at least 3 victims by groups.

## Usage

The application takes 3 arguments : `<smtp.adress> <smtp.port> <number of groups>`

The application needs to have content in the `data` folder.

## Mock server

If you want to test the application without using a real SMTP server, you can use [MockMock](https://github.com/tweakers-dev/MockMock). There is a link to a jar file in the README of the linked project.

