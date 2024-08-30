# Initial step: Ask for user's identity
user_name = input("Please enter your name: ")
print(f"Hello, {user_name}! Let's analyze the event logs for concurrency detection.\n")

# Function to detect concurrency between two events
def is_concurrent(clock1, clock2):
    less_than = all(c1 <= c2 for c1, c2 in zip(clock1, clock2))
    greater_than = all(c1 >= c2 for c1, c2 in zip(clock1, clock2))
    # Events are concurrent if neither is strictly before the other
    return not less_than and not greater_than

# Function to detect concurrency in event logs
def detect_concurrency(event_logs):
    print("\nConcurrency Detection:")
    for i in range(len(event_logs)):
        for j in range(i + 1, len(event_logs)):
            event1, clock1 = event_logs[i]
            event2, clock2 = event_logs[j]
            if is_concurrent(clock1, clock2):
                print(f"Events '{event1}' and '{event2}' are concurrent.")

# Sample event logs from multiple processes
sample_event_logs = [
    ("P0 internal event", [1, 0, 0]),
    ("P1 sends message to P2", [0, 1, 0]),
    ("P2 receives message from P1", [0, 2, 1]),
    ("P0 receives message from P2", [2, 0, 2]),
    ("P0 internal event", [3, 0, 2]),
    ("P2 internal event", [0, 2, 3])
]

# Detect concurrency in the sample event logs
detect_concurrency(sample_event_logs)
