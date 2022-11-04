package control;

import java.awt.Graphics;
import java.util.ArrayList;

import control.Background.AddDuplicatedBackgroundNameException;
import control.Background.AddNullBackgroundNameException;
import control.Background.AddReservedBackgroundNameException;
import control.Script.AddDuplicatedScriptNameException;
import control.Script.AddNullScriptNameException;
import control.Sprite.AddDuplicatedSpriteNameException;
import control.Sprite.AddNullSpriteNameException;
import equipments.StageObject;

/**
 * มีหน้าที่ทำให้วัตถุที่เป็น StageObject ถูกทำงาน<br>
 * อีกทั้งยังคอยจัดตำแหน่งและแสดงพื้นหลังด้วย
 * 
 * @author ณัชพล จำปานิน
 */
public class Handlers {
	static final ArrayList<Background> backgrounds = new ArrayList<Background>();
	static final ArrayList<Script> scripts = new ArrayList<Script>();
	static final ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	static transient ArrayList<Script> clonedScripts = new ArrayList<Script>();
	static transient ArrayList<Sprite> clonedSprites = new ArrayList<Sprite>();

	private static Background showingBackground;

	Handlers() {
		final Background defaultBackground = new Background();
		backgrounds.add(defaultBackground);
		showingBackground = defaultBackground;
	}

	static synchronized final void tick() {
		MouseInput.tick();
		try {
			Sensor.tick();
		} catch (Exception e) {
			e.printStackTrace();
		}
		showingBackground.tick();
		clonedScripts = scripts;
		for (Script i : clonedScripts)
			i.tick();
		clonedSprites = sprites;
		for (Sprite i : clonedSprites)
			i.tick();
	}

	static synchronized final void render(Graphics g) {
		showingBackground.render(g);
		clonedSprites = sprites;
		for (Sprite i : clonedSprites)
			i.render(g);
	}

	/**
	 * StageObject ที่เพิ่มเป็น null
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class AddNullStageObjectException extends Exception {
		private static final long serialVersionUID = 1L;

		AddNullStageObjectException() {
			super("StageObject เป็น null ไม่ได้");
		}
	}

	/**
	 * เพิ่ม background ซ้ำ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class AddDuplicatedBackgroundException extends Exception {
		private static final long serialVersionUID = 1L;

		AddDuplicatedBackgroundException() {
			super("เพิ่ม background ซ้ำไม่ได้");
		}
	}

	/**
	 * เพิ่ม background ให้ทำงาน
	 * 
	 * @param background พื้นหลังที่อยู่ในรูปของ StageObject
	 * @return background ที่ได้ถูกเพิ่ม
	 * @throws AddNullBackgroundNameException       background เป็น null
	 * @throws AddReservedBackgroundNameException   ตั้งชื่อ background เป็น
	 *                                              "defaultBackground"
	 * @throws AddDuplicatedBackgroundNameException ตั้งชื่อ background ซ้ำ
	 * @throws AddNullStageObjectException          stageObject เป็น null
	 * @throws AddDuplicatedBackgroundException     เพิ่ม background ซ้ำ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final StageObject addBackground(final StageObject background)
			throws AddNullBackgroundNameException, AddReservedBackgroundNameException,
			AddDuplicatedBackgroundNameException, AddNullStageObjectException, AddDuplicatedBackgroundException {
		if (background == null)
			throw new AddNullStageObjectException();
		Background controlBackground = new Background(background, background.getName());
		if (backgrounds.contains(controlBackground))
			throw new AddDuplicatedBackgroundException();
		backgrounds.add(controlBackground);
		background.setup();
		return background;
	}

	/**
	 * script ที่เพิ่มเป็น null
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class AddNullScriptException extends Exception {
		private static final long serialVersionUID = 1L;

		AddNullScriptException() {
			super("เพิ่ม script ที่เป็น null ไม่ได้");
		}
	}

	/**
	 * เพิ่ม script ซ้ำ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class AddDuplicatedScriptException extends Exception {
		private static final long serialVersionUID = 1L;

		AddDuplicatedScriptException() {
			super("เพิ่ม script ซ้ำไม่ได้");
		}
	}

	/**
	 * เพิ่ม script ให้ทำงาน
	 * 
	 * @param script บทที่อยู่ในรูปของ StageObject
	 * @return บทที่ได้ถูกเพิ่ม
	 * @throws AddNullStageObjectException      script เป็น null
	 * @throws AddDuplicatedScriptNameException ตั้งชื่อ script ซ้ำ
	 * @throws AddNullScriptNameException       ตั้งชื่อ script เป็น null
	 * @throws AddDuplicatedScriptException     เพิ่ม script ซ้ำ
	 * @author ณัชพล จำปานิน
	 */
	public static final StageObject addScript(final StageObject script) throws AddNullStageObjectException,
			AddDuplicatedScriptNameException, AddNullScriptNameException, AddDuplicatedScriptException {
		if (script == null)
			throw new AddNullStageObjectException();
		final Script controlScript = new Script(script, script.getName());
		if (scripts.contains(controlScript))
			throw new AddDuplicatedScriptException();
		script.setup();
		scripts.add(controlScript);
		return script;
	}

	/**
	 * เพิ่ม sprite ซ้ำ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class AddDuplicatedSpriteException extends Exception {
		private static final long serialVersionUID = 1L;

		AddDuplicatedSpriteException() {
			super("เพิ่ม sprite ซ้ำไม่ได้");
		}
	}

	/**
	 * เพิ่ม sprite ให้ทำงาน
	 * 
	 * @param sprite sprite ที่ต้องการเพิ่ม
	 * @return sprite ในรูปของ StageObject
	 * @throws AddNullStageObjectException      sprite ที่เพิ่มเป็น null
	 * @throws AddDuplicatedSpriteNameException sprite มีชื่อซ้ำ
	 * @throws AddNullSpriteNameException       sprite มีชื่อเป็น null
	 * @throws AddDuplicatedScriptException     เพิ่ม sprite ซ้ำ
	 * @author ณัชพล จำปานิน
	 */
	public static final StageObject addSprite(final StageObject sprite) throws AddNullStageObjectException,
			AddDuplicatedSpriteNameException, AddNullSpriteNameException, AddDuplicatedScriptException {
		if (sprite == null)
			throw new AddNullStageObjectException();
		final Sprite controlSprite = new Sprite(sprite, sprite.getName());
		if (sprites.contains(controlSprite))
			throw new AddDuplicatedScriptException();
		sprite.setup();
		sprites.add(controlSprite);
		return sprite;
	}

	private static final int getBackgroundIndex(String name) {
		for (int i = 0; i < backgrounds.size(); i++)
			if (backgrounds.get(i).name.compareTo(name) == 0)
				return i;
		return -1;
	}

	private static final int getScriptIndex(String name) {
		for (int i = 0; i < scripts.size(); i++)
			if (scripts.get(i).name.compareTo(name) == 0)
				return i;
		return -1;
	}

	private static final int getSpriteIndex(final String name) {
		for (int i = 0; i < sprites.size(); i++)
			if (sprites.get(i).name.compareTo(name) == 0)
				return i;
		return -1;
	}

	/**
	 * ดึงค่าเลเยอร์ sprite ที่ไม่ได้มีชือแบบนั้นอยู๋
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class GetNotExistedSpriteLayerException extends Exception {
		private static final long serialVersionUID = 1L;

		GetNotExistedSpriteLayerException() {
			super("ดึงค่าเลเยอร์จาก sprite ที่ไม่รู้จักชื่อมาก่อนไม่ได้");
		}
	}

	/**
	 * ดึงเลเยอร์จากชื่อ sprite
	 * 
	 * @param name ขื่อ sprite
	 * @return ค่าเลเยอร์
	 * @throws GetNotExistedSpriteLayerException ไม่รู้จักชื่อ sprite
	 * @author ณัชพล จำปานิน
	 */
	public static final int getSpriteLayer(final String name) throws GetNotExistedSpriteLayerException {
		final int ret = getSpriteIndex(name);
		if (ret >= 0)
			return ret;
		throw new GetNotExistedSpriteLayerException();
	}

	/**
	 * ค่าเลเยอร์ที่มีค่ามากที่สุดของ sprite
	 * 
	 * @return จำนวน sprite ที่มีทั้งหมด
	 */
	public static final int getSpriteMax() {
		return sprites.size();
	}

	/**
	 * เลเยอร์มีค่าติดลบ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class LayerCannotBeNegativeException extends Exception {
		private static final long serialVersionUID = 1L;

		LayerCannotBeNegativeException() {
			super("เลเยอร์มีค่าติดลบไม่ได้");
		}
	}

	/**
	 * เลเยอร์มีค่ามากเกินกว่าจำนวน sprite ที่มี
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class LayerOutOfRangeException extends Exception {
		private static final long serialVersionUID = 1L;

		LayerOutOfRangeException() {
			super("เลเยอร์มีค่ามากเกินกว่าจำนวน sprite ที่มีไม่ได้");
		}
	}

	/**
	 * ดึงค่าเลเยอร์จากชื่อ sprite
	 * 
	 * @param layer ค่าเลเยอร์
	 * @return
	 * @throws LayerCannotBeNegativeException ค่าเลเยอร์ติดลบ
	 * @throws LayerOutOfRangeException       ค่าเลเยอร์มีค่าเกินจำนวน sprite
	 * @author ณัชพล จำปานิน
	 */
	public static final String getSpriteName(final int layer)
			throws LayerCannotBeNegativeException, LayerOutOfRangeException {
		if (layer < 0)
			throw new LayerCannotBeNegativeException();
		if (layer >= sprites.size())
			throw new LayerOutOfRangeException();
		return sprites.get(layer).name;
	}

	/**
	 * ขยับ sprite ที่ไม่รู้จักชื่อ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class MoveNotExistedSpriteException extends Exception {
		private static final long serialVersionUID = 1L;

		MoveNotExistedSpriteException() {
			super("ขยับ sprite ที่ไม่รู้จักชื่อไม่ได้");
		}
	}

	/**
	 * ขยับ sprite เพื่อเลื่อนไปข้างหน้าหรือข้างหลัง
	 * 
	 * @param name  ชื่อ sprite
	 * @param times จำนวนครั้งที่เลื่อน ถ้าค่าเป็นบวกจะแปลว่าเลื่อนไปข้างหน้า<br>
	 *              ถ้าค่าเป็นลบจะแปลว่าเลื่อนไปข้างหลัง
	 * @return ถ้าค่าที่ส่งกลับเป็นจริง จะแปลว่าได้เลื่อน
	 *         มิฉะนั้นจะแปลว่าไม่ได้เลื่อน
	 * @throws MoveNotExistedSpriteException ชื่อ sprite ไม่รู้จัก
	 * @author ณัชพล จำปานิน
	 */
	public static final boolean moveSprite(final String name, int times) throws MoveNotExistedSpriteException {
		int index = getSpriteIndex(name);
		if (index >= 0)
			if (times > 0) {
				if (index < sprites.size() - 1) {
					while (times > 0 && index < sprites.size() - 1) {
						Sprite front = sprites.get(index + 1);
						sprites.set(index + 1, sprites.get(index));
						sprites.set(index, front);
						index++;
						times--;
					}
					return true;
				} else
					return false;
			} else if (times < 0) {
				if (index > 0) {
					while (times < 0 && index > 0) {
						Sprite back = sprites.get(index - 1);
						sprites.set(index - 1, sprites.get(index));
						sprites.set(index, back);
						index--;
						times++;
					}
					return true;
				} else
					return false;
			} else
				return true;
		throw new MoveNotExistedSpriteException();
	}

	/**
	 * ดึงค่าความต่างของเลเยอร์ระหว่างสอง sprite
	 * 
	 * @param currentSprite sprite ที่เป็นตัวตั้ง
	 * @param targetSprite  sprite ที่เป็นตัวลบ
	 * @return ค่าที่คิดจาก เลเยอร์ของ (currentSprite - ค่าเลเยอร์ของ targetSprite)
	 * @throws GetNotExistedSpriteLayerException มี sprite ที่ไม่รู้จักชื่อ
	 * @author ณัชพล จำปานิน
	 */
	public static final int getSpriteDistance(final String currentSprite, final String targetSprite)
			throws GetNotExistedSpriteLayerException {
		return getSpriteLayer(currentSprite) - getSpriteLayer(targetSprite);
	}

	/**
	 * สลับเลเยอร์ระหว่างสอง sprite<br>
	 * โดยจะอิงจากชื่อของทั้งสอง sprite
	 * 
	 * @param currentSprite sprite ที่จะสลับ (จะเริ่ม sprite ไหนก็เหมือนกัน)
	 * @param targetSprite  sprite ที่จะถูกสลับ (จะเริ่ม sprite ไหนก็เหมือนกัน)
	 * @throws GetNotExistedSpriteLayerException มี sprite ที่ไม่รู้จักชื่อ
	 * @author ณัชพล จำปานิน
	 */
	public static final void swapSprite(final String currentSprite, final String targetSprite)
			throws GetNotExistedSpriteLayerException {
		final int currentSpriteIndex = getSpriteLayer(currentSprite);
		if (currentSpriteIndex < 0)
			throw new GetNotExistedSpriteLayerException();
		final int targetSpriteIndex = getSpriteLayer(targetSprite);
		if (targetSpriteIndex < 0)
			throw new GetNotExistedSpriteLayerException();
		final Sprite tempSprite = sprites.get(currentSpriteIndex);
		sprites.set(currentSpriteIndex, sprites.get(targetSpriteIndex));
		sprites.set(targetSpriteIndex, tempSprite);
	}

	/**
	 * สลับเลเยอร์ระหว่างสอง sprite โดยจะอิงจากชื่อ sprite และเลเยอร์ของ sprite
	 * 
	 * @param currentSprite sprite ที่จะสลับ (จะเริ่ม sprite ไหนก็เหมือนกัน)
	 * @param targetSprite  sprite ที่จะถูกสลับ (จะเริ่ม sprite ไหนก็เหมือนกัน)
	 * @throws GetNotExistedSpriteLayerException มี sprite ที่ไม่รู้จักชื่อ
	 * @author ณัชพล จำปานิน
	 */
	public static final void swapSprite(final String currentSprite, final int targetSprite)
			throws GetNotExistedSpriteLayerException {
		final int currentSpriteIndex = getSpriteLayer(currentSprite);
		if (currentSpriteIndex < 0)
			throw new GetNotExistedSpriteLayerException();
		if (targetSprite < 0)
			throw new GetNotExistedSpriteLayerException();
		final Sprite tempSprite = sprites.get(currentSpriteIndex);
		sprites.set(currentSpriteIndex, sprites.get(targetSprite));
		sprites.set(targetSprite, tempSprite);
	}

	/**
	 * ขยับ sprite ไปที่ตำแหน่งเฉพาะเจาะจง
	 * 
	 * @param name   ชื่อ
	 * @param target เป้าหมายที่เป็นเลเยอร์
	 * @throws GetNotExistedSpriteLayerException มี sprite ที่ไม่รู้จักชื่อ
	 * @author ณัชพล จำปานิน
	 */
	public static final void moveSpriteTo(final String name, final int target)
			throws GetNotExistedSpriteLayerException {
		swapSprite(name, target);
	}

	/**
	 * มีไว้เรียกใช้เพื่อจัดลำดับ sprite<br>
	 * โดยจะมีลำดับไล่ขึ้นไปเรื่อย ๆ<br>
	 * ทุก ๆ ครั้งที่ใช้ method
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final SpriteSorting spriteSorting = new SpriteSorting();

	/**
	 * มีหน้าที่จัดลำดับ sprite<br>
	 * โดยจะมีลำดับไล่ขึ้นไปเรื่อย ๆ<br>
	 * ทุก ๆ ครั้งที่ใช้ method
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class SpriteSorting {
		private static int layer = 0;

		/**
		 * เริ่มต้นจัดเรียง sprite แรก ให้มีเลเยอร์เป็น 0
		 * 
		 * @param name ชื่อ sprite
		 * @return มีไว้ทำให้ใช้ method ต่อเนื่องได้
		 * @throws GetNotExistedSpriteLayerException name เป็น null
		 */
		public final SpriteSorting start(final String name) throws GetNotExistedSpriteLayerException {
			layer = 0;
			moveSpriteTo(name, layer);
			layer++;
			return spriteSorting;
		}

		/**
		 * เริ่มต้นจัดเรียง sprite แรก ให้มีเลเยอร์เป็น start
		 * 
		 * @param name ชื่อ sprite
		 * @param start เลเยอร์เริ่มต้น
		 * @return มีไว้ทำให้ใช้ method ต่อเนื่องได้
		 * @throws GetNotExistedSpriteLayerException name เป็น null
		 */
		public final SpriteSorting start(final String name, final int start) throws GetNotExistedSpriteLayerException {
			layer = start;
			moveSpriteTo(name, layer);
			layer++;
			return spriteSorting;
		}

		/**
		 * จัดเรียง sprite ต่อเนื่อง โดยจะมีเลเยอร์เพิ่มขึ้นทีละ 1
		 * 
		 * @param name ชื่อ sprite
		 * @return มีไว้ทำให้ใช้ method ต่อเนื่องได้
		 * @throws GetNotExistedSpriteLayerException
		 */
		public final SpriteSorting sort(final String name) throws GetNotExistedSpriteLayerException {
			moveSpriteTo(name, layer);
			layer++;
			return spriteSorting;
		}
	}

	/**
	 * ลบ background ที่ไม่รู้จัก
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class RemoveNotExistedBackgroundException extends Exception {
		private static final long serialVersionUID = 1L;

		RemoveNotExistedBackgroundException() {
			super("ลบ background ที่ไม่รู้จักไม่ได้");
		}
	}

	/**
	 * ลบ background ที่กำลังแสดงผลอยู่
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class RemoveShowingStageException extends Exception {
		private static final long serialVersionUID = 1L;

		RemoveShowingStageException() {
			super("ลบ background ที่กำลังแสดงผลอยู่ไม่ได้");
		}
	}

	/**
	 * ลบ background ออกจากการทำงาน
	 * 
	 * @param name ชื่อ background
	 * @throws RemoveShowingStageException         ลบ background ที่กำลังแสดงผล
	 * @throws RemoveNotExistedBackgroundException ลบ background ที่ไม่รู้จักชื่อ
	 * @author ณัชพล จำปานิน
	 */
	public static final void removeBackground(final String name)
			throws RemoveShowingStageException, RemoveNotExistedBackgroundException {
		final int index = getBackgroundIndex(name);
		if (index >= 0)
			if (backgrounds.size() == 1) {
				backgrounds.remove(index);
				Background defaultBackground = new Background();
				backgrounds.add(defaultBackground);
				showingBackground = defaultBackground;
			} else if (showingBackground == backgrounds.get(index))
				throw new RemoveShowingStageException();
			else
				backgrounds.remove(index);
		else
			throw new RemoveNotExistedBackgroundException();
	}

	/**
	 * ลบ script ที่ไม่รู้จัก
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class RemoveNotExistedScriptException extends Exception {
		private static final long serialVersionUID = 1L;

		RemoveNotExistedScriptException() {
			super("ลบ script ที่ไม่รู้จักไม่ได้");
		}
	}

	/**
	 * ลบ script
	 * 
	 * @param name ชื่อ script
	 * @throws RemoveNotExistedScriptException ลบ script ที่ไม่รู้จัก
	 * @author ณัชพล จำปานิน
	 */
	public static final void removeScript(final String name) throws RemoveNotExistedScriptException {
		final int index = getScriptIndex(name);
		if (index >= 0)
			scripts.remove(index);
		else
			throw new RemoveNotExistedScriptException();
	}

	/**
	 * ลบ sprite ที่ไม่รู้จัก
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class RemoveNotExistedSpriteException extends Exception {
		private static final long serialVersionUID = 1L;

		RemoveNotExistedSpriteException() {
			super("ลบ sprite ที่ไม่รู้จักไม่ได้");
		}
	}

	/**
	 * ลบ sprite
	 * 
	 * @param name ชื่อ sprite
	 * @throws RemoveNotExistedSpriteException ลบ sprite ที่ไม่รู้จัก
	 * @author ณัชพล จำปานิน
	 */
	public static final void removeSprite(final String name) throws RemoveNotExistedSpriteException {
		final int index = getSpriteIndex(name);
		if (index >= 0)
			sprites.remove(index);
		else
			throw new RemoveNotExistedSpriteException();
	}

	/**
	 * ไม่รู้จัก background นั้น ๆ
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class NotExistedBackgroundException extends Exception {
		private static final long serialVersionUID = 1L;

		NotExistedBackgroundException() {
			super("ไม่รู้จัก background นั้น ๆ");
		}
	}

	/**
	 * ตรวจสอบว่าพื้นหลังนั้น กำลังถูกแสดงอยู่หรือไม่
	 * 
	 * @param name ชื่อ background ซึ่งเป็นพื้นหลัง
	 * @return ถ้า background นั้นกำลังแสดงอยู่ จะส่งค่ากลับเป็นจริง
	 *         มิฉะนั้นจะเป็นเท็จ
	 * @throws NotExistedBackgroundException background ไม่รู้จักชื่อ
	 * @author ณัชพล จำปานิน
	 */
	public static final boolean isBackgroundShowing(final String name) throws NotExistedBackgroundException {
		final int index = getBackgroundIndex(name);
		if (index >= 0)
			return showingBackground == backgrounds.get(index);
		else
			throw new NotExistedBackgroundException();
	}

	/**
	 * แสดง background ที่ไม่รู้จัก
	 * 
	 * @author ณัชพล จำปานิน
	 */
	public static final class ShowNotExistedBackgroundException extends Exception {
		private static final long serialVersionUID = 1L;

		ShowNotExistedBackgroundException() {
			super("Cannot show not existed background");
		}
	}

	/**
	 * แสดงพื้นหลัง
	 * 
	 * @param name ชื่อ background ซึ่งเป์นพื้นหลัง
	 * @throws ShowNotExistedBackgroundException แสดง background ที่ไม่รู้จัก
	 * @author ณัชพล จำปานิน
	 */
	public static final void showBackground(final String name) throws ShowNotExistedBackgroundException {
		final int index = getBackgroundIndex(name);
		if (index >= 0)
			showingBackground = backgrounds.get(index);
		else
			throw new ShowNotExistedBackgroundException();
	}

	static final void showBackground(final Background background) {
		showingBackground = background;
	}
}
