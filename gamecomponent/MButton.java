package cs2410.gamecomponent;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

public class MButton extends JButton {
	private static final String MAYBE_TEXT = "?";
	private static final String MARKED_TEXT = "B";
	private static int markBombWrong = 0;
	private static int clickEmptyCorrect = 0;

	private static int markBombCorrect = 0;
	private int locX;
	private int locY;
	private int neighborBombs = 0;
	private boolean hasBomb = false;
	private boolean isMaybe = false;
	private boolean isMarked = false;
	private boolean isDone = false;

	private MButton() {
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	}

	public MButton(int x, int y, int size, MButton[][] btnBoard) {
		this();
		locX = x;
		locY = y;
		this.setBounds(x * size, y * size, size, size);
	}

	public static void resetMButton() {
		markBombWrong = 0;
		clickEmptyCorrect = 0;
		markBombCorrect = 0;
	}

	public void setBomb() {
		hasBomb = true;
	}

	public boolean hasBomb() {
		return hasBomb;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone() {
		isDone = true;
	}

	public boolean isMaybe() {
		return isMaybe;
	}

	public void setMaybe(boolean isMaybe) {
		this.isMaybe = isMaybe;
	}

	public void addNeighborBomb() {
		neighborBombs++;
	}

	public int getNeighborBombs() {
		return neighborBombs;
	}

	public void gameOver() {
		setEnabled(true);
		if (hasBomb || isMarked || isMaybe) {
			clicked();
		}
		setEnabled(false);
	}

	public void clicked() {
		if (isEnabled()) {
			setEnabled(false);
			setDone();
			setBorder(BorderFactory.createLoweredBevelBorder());
			if (hasBomb) {
				if (isMarked) {
					setBackground(Color.GREEN);
					setOpaque(true);
					repaint();
				} else {
					setBackground(Color.RED);
					setOpaque(true);
					repaint();
				}
			} else {
				if (isMarked) {
					setBackground(Color.YELLOW);
					setText("X");
					setOpaque(true);
					repaint();
				} else {
					clickEmptyCorrect++;
					setBackground(Color.LIGHT_GRAY.brighter());
					setOpaque(true);
					update(this.getGraphics());
					if (neighborBombs > 0) {
						setText("" + neighborBombs);
					}
				}
			}
		} else {
			return;
		}
	}

	public void rightClicked() {
		if (isMaybe) {
			if (hasBomb) {
				markBombCorrect--;
			} else {
				markBombWrong--;
			}
			isMaybe = false;
			setText("");
			setEnabled(true);
		} else if (isMarked) {
			// markUpdate = -1;
			isMarked = false;
			isMaybe = true;
			setText(MButton.MAYBE_TEXT);
		} else {
			isMarked = true;
			if (hasBomb) {
				markBombCorrect++;
			} else {
				markBombWrong++;
			}
			// markUpdate = 1;
			setText(MButton.MARKED_TEXT);
			setEnabled(false);
		}
	}

	public int getLocX() {
		return locX;
	}

	public int getLocY() {
		return locY;
	}

	public int updateMarked() {
		return markBombCorrect + markBombWrong;
	}

	public boolean noNeighborBombs() {
		if (neighborBombs > 0)
			return false;
		return true;
	}

	public static int getMarkBombCorrect() {
		return markBombCorrect;
	}

	public static int getMarkBombWrong() {
		return markBombWrong;
	}

	public static int getClickEmptyCorrect() {
		return clickEmptyCorrect;
	}
}
