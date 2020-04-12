package org.teamfelnull.kimnarutree.item;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class Evaluations {
	//エンチャント評価値
	public static int getEnchantEvaluation(Map<Enchantment, Integer> enmap) {
		int eva = 0;
		for (Entry<Enchantment, Integer> enchant : enmap.entrySet()) {
			eva += getEnchantEvaluation(enchant.getKey(), enchant.getValue());
		}
		return eva;
	}

	public static int getEnchantEvaluation(Enchantment enchant, int level) {

		float feva = ((float) level / (float) enchant.getMaxLevel())
				* (((float) enchant.getMinEnchantability(level) + (float) enchant.getMaxEnchantability(level)));

		if (enchant.isTreasureEnchantment())
			feva *= 1.5f;

		int eva = (int) feva;

		if (enchant.isCurse())
			eva *= -1;

		return eva;
	}

	//防具評価値
	@SuppressWarnings("deprecation")
	public static int getArmorEvaluation(ItemStack stack) {
		if (stack.isEmpty() || !(stack.getItem() instanceof ArmorItem))
			return 0;

		ArmorItem arm = (ArmorItem) stack.getItem();

		int eva = (arm.getDamageReduceAmount() * 30) + (int) (arm.getToughness() * 15)
				+ getEnchantEvaluation(EnchantmentHelper.getEnchantments(stack)) + (arm.getMaxDamage() / 20);

		eva = (int) ((float) eva * ((float) (stack.getMaxDamage() - stack.getDamage()) / (float) stack.getMaxDamage()));

		return eva;
	}
}
