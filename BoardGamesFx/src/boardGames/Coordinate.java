package boardGames;

public class Coordinate {
    public int fileIndex;
    public int rankIndex;

    public Coordinate(char aFile, int aRank) {
        fileIndex = aFile - 'a';
        rankIndex = aRank - 1;
    }

    public Coordinate(int aFileIndex, int aRankIndex) {
        fileIndex = aFileIndex;
        rankIndex = aRankIndex;
    }

    public char getFile() {
        // turn fileIndex into a file
        return (char) ('a' + fileIndex);
    }

    public int getRank() {
        return rankIndex + 1;
    }

    @Override
    public String toString() {
        return getFile() + Integer.toString(getRank());
    }

    @Override
    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Coordinate otherCoordinate = (Coordinate) other;
        return otherCoordinate.fileIndex == this.fileIndex
                && otherCoordinate.rankIndex == this.rankIndex;
    }

    @Override
    public int hashCode() {
        return fileIndex ^ rankIndex;
    }
}
