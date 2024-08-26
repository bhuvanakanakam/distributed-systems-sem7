import socket
import time

HOST='10.70.34.14'
PORT=9990

client_socket=socket.socket(socket.AF_INET,socket.SOCK_STREAM)

client_socket.connect((HOST,PORT))

while True:
    message=input(f"Client: ")
    start_time=time.time()
    client_socket.sendall(message.encode())
    if(message=="QUIT" or time.time()-start_time>10):
        break
    response=client_socket.recv(1024).decode()
    print(f"Server : {response}")
    if(response=="QUIT"):
        break

client_socket.close()