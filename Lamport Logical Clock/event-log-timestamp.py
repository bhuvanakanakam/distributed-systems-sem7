class Process:
    def __init__(self, process_id):
        self.process_id = process_id
        self.clock = 0
        self.event_log = []

    def increment_clock(self):
        self.clock += 1

    def log_event(self, event):
        self.event_log.append((event, self.clock))

    def internal_event(self):
        self.increment_clock()
        event = f"P{self.process_id} performs internal event"
        self.log_event(event)
        print(f"{event} at time {self.clock}")

    def send_message(self, receiver):
        self.increment_clock()
        event = f"P{self.process_id} sends message to P{receiver.process_id}"
        self.log_event(event)
        print(f"{event} at time {self.clock}")
        receiver.receive_message(self.clock)

    def receive_message(self, sender_clock):
        self.clock = max(self.clock, sender_clock) + 1
        event = f"P{self.process_id} receives message with sender clock {sender_clock}"
        self.log_event(event)
        print(f"{event} and updates clock to {self.clock}")

    def show_log(self):
        print(f"\nEvent Log for Process {self.process_id}:")
        for event, timestamp in self.event_log:
            print(f"Timestamp {timestamp}: {event}")

# Initialize processes
process1 = Process(1)
process2 = Process(2)
process3 = Process(3)

# Simulate events
process1.internal_event()       # P1 performs an internal event
process2.send_message(process3) # P2 sends a message to P3
process3.send_message(process1) # P3 sends a message to P1
process1.receive_message(process3.clock)  # P1 receives a message from P3
process1.internal_event()       # P1 performs another internal event
process3.internal_event()       # P3 performs an internal event

# Display the logs
process1.show_log()
process2.show_log()
process3.show_log()
