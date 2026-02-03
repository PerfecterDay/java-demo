package com.gtja;

enum Side {NONE, LEFT, RIGHT}

public class ChainLink {
    private ChainLink left, right;

    public void append(ChainLink rightPart) {
        if (this.right != null)
            throw new IllegalStateException("Link is already connected.");

        this.right = rightPart;
        rightPart.left = this;
    }

    public Side longerSide() {
        // all null, return none
        if (this.left == null && this.right == null) {
            return Side.NONE;
        }

        // left == null && right != null, return right
        if (this.left == null) {
            return Side.RIGHT;
        }

        // right == null && left != null, return left
        if (this.right == null) {
            return Side.LEFT;
        }

        // both not null and loop
        int leftI = 0, rightI = 0;
        ChainLink rightP = this.right;
        ChainLink leftP = this.left;
        while (rightP != null) {
            rightI++;
            rightP = rightP.right;
            if (rightP == this) return Side.NONE; // loop
        }

        while (leftP != null) {
            leftI++;
            leftP = leftP.left;
            if (leftP == this) return Side.NONE; // loop
        }

        if (leftI == rightI) return Side.NONE;
        return leftI > rightI ? Side.LEFT : Side.RIGHT;
    }

    public static void main(String[] args) {
        ChainLink left = new ChainLink();
        ChainLink middle = new ChainLink();
        ChainLink right = new ChainLink();
        left.append(middle);
        middle.append(right);
        System.out.println(left.longerSide());
    }
}
