import socket

def start_server():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # Create a socket object
    host = '0.0.0.0'  # Get local machine name
    port = 9999

    # Bind the socket to the address and port
    server_socket.bind((host, port))
    
    # Start listening for incoming connections (with a max queue of 5)
    server_socket.listen(5)
    print(f"Server started! Listening on {host}:{port}")

    while True:
        # Accept an incoming connection
        client_socket, addr = server_socket.accept()
        print(f"Got a connection from {addr}")
        
        # Send a thank you message to the client
        message = 'Thank you for connecting\n'
        client_socket.send(message.encode('ascii'))
        
        # Close the client connection
        client_socket.close()

if __name__ == "__main__":
    start_server()
