import socket

def start_server():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # Create a socket object
    host = '0.0.0.0'  # Bind to all interfaces
    port = 9990

    # Bind the socket to the address and port
    server_socket.bind((host, port))
    
    # Start listening for incoming connections (with a max queue of 5)
    server_socket.listen(5)
    print(f"Server started! Listening on {host}:{port}")

    while True:
        # Accept an incoming connection
        client_socket, addr = server_socket.accept()
        print(f"Got a connection from {addr}")
        
        try:
            while True:
                # Receive message from the client
                client_message = client_socket.recv(1024).decode('ascii')
                if client_message.lower() == 'exit':
                    print("Client has disconnected.")
                    break

                print(f"Client: {client_message}")
                
                # Send message to the client
                server_message = input("Server: ")
                client_socket.send(server_message.encode('ascii'))
                
                if server_message.lower() == 'exit':
                    print("Server is shutting down the connection.")
                    break
        except Exception as e:
            print(f"An error occurred: {e}")
        finally:
            # Close the client connection
            client_socket.close()
            print("Connection closed.")

if __name__ == "__main__":
    start_server()
