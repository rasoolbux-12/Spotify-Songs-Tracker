package Project;

import java.io.*;
import java.util.*;

// Node class for Linked List
class SongNode {
    String name;
    int streams;
    SongNode next;

    public SongNode(String name, int streams) {
        this.name = name;
        this.streams = streams;
        this.next = null;
    }
}

// Linked List for Playlist
class Playlist {
    SongNode head;

    // Add song
    public void addSong(String name, int streams) {
        SongNode newSong = new SongNode(name, streams);
        if (head == null) {
            head = newSong;
        } else {
            SongNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newSong;
        }
        System.out.println("Added: " + name);
    }

    // Remove song
    public void removeSong(String name) {
        SongNode temp = head;
        SongNode prev = null;
        while (temp != null && !temp.name.equalsIgnoreCase(name)) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Song not found");
            return;
        }
        if (prev != null) prev.next = temp.next;
        else head = temp.next;
        System.out.println("Removed: " + name);
    }

    // Display playlist
    public void display() {
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        }
        System.out.println("\n--- Playlist ---");
        SongNode temp = head;
        while (temp != null) {
            System.out.println(temp.name + " (" + temp.streams + " streams)");
            temp = temp.next;
        }
    }

    // Convert Linked List to Array
    public SongNode[] toArray() {
        int count = 0;
        SongNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }

        SongNode[] arr = new SongNode[count];
        temp = head;
        int i = 0;
        while (temp != null) {
            arr[i++] = temp;
            temp = temp.next;
        }
        return arr;
    }

 // Load songs from CSV file
    public void loadFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                // Split only on commas that are not inside quotes
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Safety check — make sure we have enough columns
                if (data.length > 7) {
                    String songName = data[0].replaceAll("\"", "").trim();
                    String streamStr = data[7].replaceAll("\"", "").trim();

                    // Remove commas from numbers like 1,234,567,890
                    streamStr = streamStr.replace(",", "");

                    // Check if streamStr is numeric
                    if (!streamStr.isEmpty() && streamStr.matches("\\d+(\\.\\d+)?")) {
                        double streamValue = Double.parseDouble(streamStr);
                        int streams = (int) streamValue;
                        addSong(songName, streams);
                    }
                }
            }

            System.out.println("\nSongs loaded successfully from file!");
        } catch (Exception e) {
            System.out.println("Error loading CSV file: " + e.getMessage());
        }
    }


}

// Binary Search class
class SearchSongs {
    public static SongNode binarySearch(SongNode[] songs, String target) {
        int left = 0, right = songs.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int check = songs[mid].name.compareToIgnoreCase(target);
            if (check == 0)
                return songs[mid];
            else if (check < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return null;
    }
}

// Stack for recently played songs
class RecentSongs {
    Stack<String> stack = new Stack<>();
    int limit = 5;

    public void playSong(String song) {
        if (stack.size() >= limit)
            stack.remove(0);
        stack.push(song);
    }

    public void showRecent() {
        System.out.println("\nLast Played Songs:");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println(stack.get(i));
        }
    }
}

// Queue for upcoming songs
class UpcomingSongs {
    Queue<String> queue = new LinkedList<>();

    public void addSong(String song) {
        queue.add(song);
        System.out.println("Added to upcoming: " + song);
    }

    public void playNext() {
        if (!queue.isEmpty()) {
            String song = queue.poll();
            System.out.println("Now Playing: " + song);
        } else {
            System.out.println("No songs in upcoming queue.");
        }
    }

    public void showQueue() {
        System.out.println("\nUpcoming Songs: " + queue);
    }
}

// Main class
public class SpotifySongsTracker {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        RecentSongs recent = new RecentSongs();
        UpcomingSongs upcoming = new UpcomingSongs();

        // Load dataset file
        String filePath = "C:\\Users\\User\\Desktop\\DSA\\DSACOURSE\\src\\Project\\Most Streamed Spotify Songs 2024.csv";
        playlist.loadFromCSV(filePath);

        
     // Convert playlist to array for sorting/searching
     SongNode[] songsArr = playlist.toArray();

     // Sort alphabetically (for binary search)
     for (int i = 0; i < songsArr.length; i++) {
         for (int j = 0; j < songsArr.length - 1 - i; j++) {
             if (songsArr[j].name.compareToIgnoreCase(songsArr[j + 1].name) > 0) {
                 SongNode temp = songsArr[j];
                 songsArr[j] = songsArr[j + 1];
                 songsArr[j + 1] = temp;
             }
         }
     }

     // Sort songs by streams in descending order (for Top 10)
     SongNode[] songsByStreams = playlist.toArray();
     for (int i = 0; i < songsByStreams.length; i++) {
         for (int j = 0; j < songsByStreams.length - 1 - i; j++) {
             if (songsByStreams[j].streams < songsByStreams[j + 1].streams) {
                 SongNode temp = songsByStreams[j];
                 songsByStreams[j] = songsByStreams[j + 1];
                 songsByStreams[j + 1] = temp;
             }
         }
     }

     // Menu
     Scanner sc = new Scanner(System.in);
     int choice;
     System.out.println("\n===============================");
     System.out.println("      SPOTIFY SONGS TRACKER ");
     System.out.println("===============================");
     
     do {
         System.out.println("\n Spotify Songs Tracker Menu ");
         System.out.println("1. Display Playlist");
         System.out.println("2. Search Song by Name");
         System.out.println("3. View Recently Played Songs");
         System.out.println("4. View Upcoming Songs");
         System.out.println("5. Play Next Upcoming Song");
         System.out.println("6. Show Top 10 Most Streamed Songs");
         System.out.println("7. Add a Song to Upcoming Queue");
         System.out.println("8. Play a Song (adds to Recent)");
         System.out.println("0. Exit");
         System.out.print("Enter your choice: ");
         choice = sc.nextInt();
         sc.nextLine(); // consume newline

         switch (choice) {
             case 1:
                 playlist.display();
                 break;
             case 2:
                 System.out.print("Enter song name to search: ");
                 String name = sc.nextLine();
                 SongNode result = SearchSongs.binarySearch(songsArr, name);
                 if (result != null)
                     System.out.println(" Found: " + result.name + " (" + result.streams + " streams)");
                 else
                     System.out.println(" Song not found.");
                 break;
             case 3:
                 recent.showRecent();
                 break;
             case 4:
                 upcoming.showQueue();
                 break;
             case 5:
                 upcoming.playNext();
                 break;
             case 6:
                 System.out.println("\n Top 10 Most Streamed Songs:");
                 for (int i = 0; i < Math.min(10, songsByStreams.length); i++) {
                     System.out.println((i + 1) + ". " + songsByStreams[i].name + " (" + songsByStreams[i].streams + " streams)");
                 }
                 break;
             case 7:
                 System.out.print("Enter song name to add to upcoming: ");
                 String upSong = sc.nextLine();
                 upcoming.addSong(upSong);
                 break;
             case 8:
                 System.out.print("Enter song name you played: ");
                 String played = sc.nextLine();
                 recent.playSong(played);
                 System.out.println(" Playing now: " + played);
                 break;
             case 0:
                 System.out.println(" Exiting... Goodbye!");
                 break;
             default:
                 System.out.println("Invalid choice, try again.");
         }

     } while (choice != 0);

     sc.close();
     
    }
}
