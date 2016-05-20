/**
 * 抽取特征
 * Parse 对给定的候选句子进行分词，结果存入parse.txt，用于之后提取特征
 * Features 调用4个特征提取对象，并将4个特征混合分写文件。
 * 	F1_Loc_nvn 提取词组每个词对应的Bagwords的位置。
 * 	F2_Distance_nvn 提取词组中的距离
 *  F3_Words 提取词组中包含的词
 *  F4_MDP 利用已得到的依存句法分析结果得到MDP
 */
/**
 * @author Joen
 *
 */
package bistu.idcc.features;