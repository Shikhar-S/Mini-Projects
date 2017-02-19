import ssl
from socket import *
import base64

endmsg='\r\n.\r\n'

mailserver = "smtp.gmail.com"
port = 465 #SSL port

clientSocket = socket(AF_INET, SOCK_STREAM)
ssl_clientSocket = ssl.wrap_socket(clientSocket) 
ssl_clientSocket.connect((mailserver, port))
#########################################
recv = ssl_clientSocket.recv(1024) # recv = 220 smtp.google.com 
if(recv[:3]!='220'):
	print 'Can not connect error code is ',recv[:3]
else:
	print 'TCP Connection Established'
###########################################
heloCommand = 'EHLO Shikhar.example.com\r\n'
ssl_clientSocket.send(heloCommand)
recv1 = ssl_clientSocket.recv(1024) #must be 250 to continue

if(recv1[:3]!='250'):
	print 'SMTP server not accepting connection'
else:
	print 'Connection established with smtp server'

#########################################
#Authorize
username=raw_input("Enter username/email id: ")
password=raw_input("Enter password: ")
toEncode='\0'+username+'\0'+password
authcommand=('AUTH PLAIN %s \r\n' % (base64.b64encode(toEncode)))
ssl_clientSocket.send(authcommand)
recv=ssl_clientSocket.recv(2048) #235 Accepted
if(recv[:3]!='235'):
	print 'Error with signIn',recv
else:
	print 'Authentication Successful!'
#######################################

mailFromCommand = 'MAIL From: <%s>\r\n' % (username)
ssl_clientSocket.send(mailFromCommand)
recv=ssl_clientSocket.recv(1024)
if(recv[:3]=='250'):
	recepient=raw_input("Enter email id of recepient: ")
	rcptToCommand = 'RCPT To: <%s>\r\n' % (recepient)
	ssl_clientSocket.send(rcptToCommand)
	recv=ssl_clientSocket.recv(1024)
	if(recv[:3]=='250'):
		ssl_clientSocket.send('DATA\r\n')
		recv=ssl_clientSocket.recv(1024)
		if(recv[:3]=='354'):
			msg=raw_input("Enter message : \n")
			msg+=endmsg
			ssl_clientSocket.send(msg)
			recv=ssl_clientSocket.recv(1024)
			if(recv[:3]=='250'):
				print 'mail sent!'
				ssl_clientSocket.send('QUIT\r\n')
				recv=ssl_clientSocket.recv(1024)
				if(recv[:3]=='221'):
					print 'Quitting now!'
				else:
					print 'Error while Quitting',recv
			else:
				print 'Mail not sent',recv
		else:
			print 'Not accepting Data',recv
	else:
		print 'Recepient invalid',recv
else:
	print 'Sender invalid',recv

