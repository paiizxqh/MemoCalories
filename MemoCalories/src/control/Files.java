package control;

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import equipments.StageObject;

/**
 * ทำหน้าที่บันทึกไฟล์ที่อาจเป็นภาพหรือเสียง<br>
 * เพื่อนำมาเรียกใช้ในภายหลัง
 * 
 * @author ณัชพล จำปานิน
 */
public class Files {
	private static String path = "";
	
	final void setPath() {
		String path = this.getClass().getClassLoader().getResource("control/Files.class").toString();
		for (int i = 6; i < path.length() - 23; i++) {
			if (path.toCharArray()[i] == '/')
				Files.path += '\\';
			else
				Files.path += path.toCharArray()[i];
		}
			
	}
	
	private static record FileData(String name, File file) {
	}

	private static final ArrayList<FileData> sounds = new ArrayList<FileData>();
	private static final ArrayList<FileData> images = new ArrayList<FileData>();
	private static final ArrayList<FileData> saves = new ArrayList<FileData>();

	/**
	 * ชื่อไฟล์ที่บันทึกไม่รู้จัก
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class NotExistedFileNameException extends Exception {
		private static final long serialVersionUID = 1L;

		NotExistedFileNameException() {
			super("ใช้ชื่อไฟล์ที่ไม่รู้จักไม่ได้");
		}
	}

	private static final int getFileIndex(final String name, final ArrayList<FileData> target) {
		for (int i = 0; i < target.size(); i++)
			if (target.get(i).name.compareTo(name) == 0)
				return i;
		return -1;
	}

	/**
	 * เพิ่มชื่อไฟล์ซ้ำ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class AddDuplicatedFileNameException extends Exception {
		private static final long serialVersionUID = 1L;

		AddDuplicatedFileNameException() {
			super("เพื่อชื่อไฟล์ซ้ำไม่ได้");
		}
	}

	/**
	 * เพิ่มตำแหน่งไฟล์ซ้ำ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class AddDuplicatedFilePathException extends Exception {
		private static final long serialVersionUID = 1L;

		AddDuplicatedFilePathException() {
			super("เพื่อตำแหน่งไฟล์ซ้ำไม่ได้");
		}
	}

	/**
	 * บันทึกไฟล์เสียงเก็บไว้
	 * 
	 * @param name ชื่อไฟล์ที่ต้องการบันทึก
	 * @param fileName ชื่อไฟล์จริง ต้องเป็นนามสกุล .wav เท่านั้น
	 * @throws AddDuplicatedFileNameException ตั้งชื่อไฟล์ซ้ำ
	 * @throws AddDuplicatedFilePathException ตั้งตำแหน่งไฟล์ซ้ำ
	 * @author ณัชพล จำปานิน
	 */
	public static final void addSound(final String name, final String fileName)
			throws AddDuplicatedFileNameException, AddDuplicatedFilePathException {
		if (getFileIndex(name, sounds) >= 0)
			throw new AddDuplicatedFileNameException();
		File file = new File(path + "data\\sounds\\" + fileName);
		for (FileData i : sounds)
			if (i.file.equals(file))
				throw new AddDuplicatedFilePathException();
		sounds.add(new FileData(name, file));
	}

	/**
	 * เล่นไฟล์เสียงที่ได้บันทึก
	 * 
	 * @param name ชื่อไฟล์เสียงที่ได้บันทึก
	 * @throws NotExistedFileNameException ไฟล์เสียงมีชื่อไม่รู้จัก
	 * @author ณัชพล จำปานิน
	 */
	public static final void playSound(final String name) throws NotExistedFileNameException {
		final int index = getFileIndex(name, sounds);
		if (index >= 0)
			new Thread(new Runnable() {
				public void run() {
					try {
						Clip clip;
						clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(sounds.get(index).file));
						clip.start();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		else
			throw new NotExistedFileNameException();
	}

	/**
	 * บันทึกไฟล์ภาพเก็บไว้
	 * 
	 * @param name ชื่อไฟล์ที่ต้องการบันทึก
	 * @param fileName ชื่อไฟล์จริง จะเป็นนามสกุล .jpg หรือ .png ก็ได้
	 * @throws AddDuplicatedFileNameException ตั้งชื่อไฟล์ซ้ำ
	 * @throws AddDuplicatedFilePathException ตั้งตำแหน่งไฟล์ซ้ำ
	 * @author ณัชพล จำปานิน
	 */
	public static final void addImage(final String name, final String fileName)
			throws AddDuplicatedFileNameException, AddDuplicatedFilePathException {
		if (getFileIndex(name, images) >= 0)
			throw new AddDuplicatedFileNameException();
		File file = new File(path + "data\\images\\" + fileName);
		for (FileData i : images)
			if (i.file.equals(file))
				throw new AddDuplicatedFilePathException();
		images.add(new FileData(name, file));
	}

	/**
	 * วาดภาพที่ได้บันทึก มีตำแหน่งที่วาดเป็น (0, 0)
	 * 
	 * @param name ชื่อไฟล์ภาพที่ได้บันทึก
	 * @param g    กราฟิกส์
	 * @throws NotExistedFileNameException ไฟล์เสียงไม่รู้จักชื่อ
	 */
	public static final void paintImage(final String name, final Graphics g) throws NotExistedFileNameException {
		final int index = getFileIndex(name, images);
		if (index >= 0)
			try {
				g.drawImage(ImageIO.read(images.get(index).file), 0, 0, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			throw new NotExistedFileNameException();
	}

	/**
	 * วาดภาพที่ได้บันทึก มีตำแหน่งที่วาดเป็น (x, y)
	 * 
	 * @param name ชื่อไฟล์ภาพที่ได้บันทึก
	 * @param g    กราฟิกส์
	 * @param x    ตำแหน่งที่ต้องการวาดในแนวแกน x คิดจากซ้ายไปขวา
	 * @param y    ตำแหน่งที่ต้องการวาดในแนวแกน y คิดจากบนลงล่าง
	 * @throws NotExistedFileNameException ไฟล์เสียงไม่รู้จักชื่อ
	 */
	public static final void paintImage(final String name, final Graphics g, final int x, final int y)
			throws NotExistedFileNameException {
		final int index = getFileIndex(name, images);
		if (index >= 0)
			try {
				g.drawImage(ImageIO.read(images.get(index).file), x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			throw new NotExistedFileNameException();
	}

	/**
	 * เพิ่มการบันทึกวัตถุ
	 * 
	 * @param name ชื่อ
	 * @param fileName ชื่อไฟล์จริง ต้องเป็นนามสกุล .sav เท่านั้น
	 * @throws AddDuplicatedFileNameException ตั้งชื่อไฟล์ซ้ำ
	 * @throws AddDuplicatedFilePathException ตั้งตำแหน่งไฟล์ซ้ำ
	 * @author ณัชพล จำปานิน
	 */
	public static final void addSave(final String name, final String fileName)
			throws AddDuplicatedFileNameException, AddDuplicatedFilePathException {
		if (getFileIndex(name, saves) >= 0)
			throw new AddDuplicatedFileNameException();
		File file = new File(path + "data\\saves" + fileName);
		for (FileData i : saves)
			if (i.file.equals(file))
				throw new AddDuplicatedFilePathException();
		saves.add(new FileData(name, file));
	}

	/**
	 * เขียนบันทึกวัตถุ
	 * 
	 * @param name        ชื่อ
	 * @param stageObject วัตถุที่ต้องการบันทึก
	 * @throws NotExistedFileNameException ไฟล์วัตถุไม่รู้จักชื่อ
	 * @author ณัชพล จำปานิน
	 */
	public static final void writeSave(final String name, StageObject stageObject) throws NotExistedFileNameException {
		final int index = getFileIndex(name, saves);
		if (index >= 0)
			try {
				FileOutputStream out = new FileOutputStream(saves.get(index).file);
				ObjectOutputStream objectOut = new ObjectOutputStream(out);
				objectOut.writeObject(stageObject);
				objectOut.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		else
			throw new NotExistedFileNameException();
	}

	/**
	 * อ่านบันทึกวัตถุ
	 * 
	 * @param name ชื่อ
	 * @return จะส่งออกวัตถุ ถ้าไม่พบการบันทึก จะส่งค่าออกเป็น null
	 * @throws NotExistedFileNameException ไฟล์วัตถุไม่รู้จักชื่อ
	 * @author ณัชพล จำปานิน
	 */
	public static final StageObject readSave(final String name) throws NotExistedFileNameException {
		final int index = getFileIndex(name, saves);
		if (index >= 0) {
			StageObject ret = null;
			FileInputStream in;
			try {
				in = new FileInputStream(saves.get(index).file);
			} catch (FileNotFoundException e) {
				return null;
			}
			ObjectInputStream objectIn;
			try {
				objectIn = new ObjectInputStream(in);
				ret = (StageObject) objectIn.readObject();
				objectIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ret;
		} else
			throw new NotExistedFileNameException();
	}
}
