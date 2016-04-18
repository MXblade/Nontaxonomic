/**
 * EntityExtraction 对给定的语料进行名词动词的标记，并清除其他符号。
 * IDF_NVN 给定N-V-N组合，计算N-N在所有文档出现的次数，以及N-V-N出现的次数。通过公式给出NVN的文档频率 ((NVN)/(NN))
 * HashMap_Patents 构建每个专利的HashSet，并将所有专利构建成一个HashMap,用于统计词在出现的文档数
 * Filter_NonNVN 清除不含nvn的句子
 * Candidate_Set 构建nvn的候选集
 */
/**
 * 对分词后的语料进行标记名词，动词，并筛选出每一句的nvn组合，作为候选集
 * 分词后的语料，整体以文件存储：sourcefile/after_parser.txt
 * 分词并去词性的语料集，每片专利以文件存储：sourcefile/afparser
 * 对分词后的语料进行名词，动词的标记，输出位置：sourcefile/candidate/TagParser.txt
 * 过滤掉不含nvn的句子，输出位置：sourcefile/candidate/after_filter.txt
 * 最后生成nvn的候选集，输出位置：sourcefile/candidate/candidate_set.txt
 */
/**
 * @author Joen
 *
 */
package bistu.idcc.candidateset;