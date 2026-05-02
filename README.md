# Spotify Songs Tracker

A Java-based DSA project that simulates a Spotify-like songs tracker using core data structures.

This project demonstrates how Linked List, Stack, Queue, Binary Search, and Sorting algorithms can be applied to solve real-world problems such as:
- Creating and managing a playlist
- Searching for songs efficiently
- Tracking recently played songs
- Managing upcoming songs
- Ranking songs by number of streams

## 🚀 Features

### 1. Playlist Management (Linked List)
- Add or remove tracks dynamically
- Display the playlist with song names and stream counts
- Songs are stored as nodes in a Singly Linked List

### 2. Song Search (Binary Search)
- Search for a song by its name in O(log n) time
- Works on a sorted playlist
- Case-insensitive search

### 3. Recently Played Songs (Stack)
- Tracks the last 5 played songs
- Uses LIFO (Last In, First Out) principle
- Automatically removes oldest entry when limit exceeds

### 4. Upcoming Songs (Queue)
- Stores songs that are "next to play"
- Uses FIFO (First In, First Out)
- Add new songs to the queue and play them in correct order

### 5. Top Streamed Songs (Sorting)
- Sorts songs by number of streams
- Displays Top 10 most-streamed songs in descending order
- Implemented using Bubble Sort

## 📂 CSV Data File

The program reads data from:

**Most Streamed Spotify Songs 2024.csv**

Example columns:

| Song Name | Artist | Album | Release Date | Streams |
|-----------|--------|-------|--------------|---------|
| Shape of You | Ed Sheeran | ÷ (Divide) | 2017 | 3,752,489,239 |
| Blinding Lights | The Weeknd | After Hours | 2020 | 3,483,495,720 |

*Only Song Name and Streams columns are used in this project.*

## ⚙️ How It Works

1. The CSV file is read line by line
2. Song name + stream count extracted
3. Commas removed from numbers (e.g., 1,234,567 → 1234567)
4. Songs added to Linked List playlist
5. Playlist converted into an array for:
   - Alphabetical sorting
   - Binary Search
6. Top 10 songs sorted by stream count
7. Recently played songs tracked using Stack
8. Upcoming songs stored in Queue

## 🧰 Data Structures Used

| Data Structure | Purpose |
|----------------|---------|
| Linked List | Playlist |
| Stack | Recently played history |
| Queue | Upcoming songs |
| Binary Search | Efficient song search |
| Bubble Sort | Sorting songs by name and stream count |

## 🎯 Purpose

This project was created as part of my **Third Semester DSA Course Project** to demonstrate the use of fundamental data structures in a real-world scenario.

---
