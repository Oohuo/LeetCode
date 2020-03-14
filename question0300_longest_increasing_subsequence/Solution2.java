package question0300_longest_increasing_subsequence;

/**
 * 二分查找法。
 *
 * 首先，我们需要一个n行的二维数组，其中第0行记录长度为1的一个"上升子序列"，第1行记录长度为2的一个"上升子序列"，...，
 * 第n - 1行记录长度为n的一个"上升子序列"。所有的这些子序列满足一个条件：它们的"结尾"是所有相同长度的"上升子序列"里面最小的。
 *
 * 以数组[10, 9, 2, 5, 3, 7, 101, 18]为例进行说明：
 *
 * 首先我们需要一个8行的数组，初始均为空：
 * [[], [], [], [], [], [], [], []]
 *
 * （1）遍历第0个元素——10，此时我们填充第0行：
 * [[10], [], [], [], [], [], [], []]
 * （2）遍历第1个元素——9，此时我们的数组如下：
 * [[9], [], [], [], [], [], [], []]
 * （3）遍历第2个元素——2，此时我们的数组如下：
 * [[2], [], [], [], [], [], [], []]
 * （4）遍历第3个元素——5，5比2大，此时我们填充第1行：
 * [[2], [2, 5], [], [], [], [], [], []]
 * （5）遍历第4个元素——3，3比2大比5小，此时我们的数组如下：
 * [[2], [2, 3], [], [], [], [], [], []]
 * （6）遍历第5个元素——7，7比3大，此时我们填充第2行：
 * [[2], [2, 3], [2, 3, 7], [], [], [], [], []]
 * （7）遍历第6个元素——101，101比7大，此时我们填充第3行：
 * [[2], [2, 3], [2, 3, 7], [2, 3, 7, 101], [], [], [], []]
 * （8）遍历第7个元素——18，18比7大，比101小，此时我们的数组如下：
 * [[2], [2, 3], [2, 3, 7], [2, 3, 7, 18], [], [], [], []]
 *
 * 遍历完成后，我们发现总共填充了3行，因此最长子序列长度就是4。
 *
 * 在整个过程中，我们只用到了第i行的最后一个元素，因此我们只需要用一个一维数组tail来保存某行的最后一个元素即可。
 *
 * 时间复杂度是O(nlogn)，其中n为nums数组的长度。空间复杂度是O(n)。
 *
 * 执行用时：1ms，击败99.58%。消耗内存：36.2MB，击败34.62%。
 */
public class Solution2 {
    public int lengthOfLIS(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) == 0) {
            return 0;
        }
        int[] tail = new int[n];
        tail[0] = nums[0];
        int end = 0; //end表示有序数组tail的最后一个已经赋值元素的索引
        for (int i = 1; i < n; i++) {
            if (nums[i] > tail[end]) {  //得到一个长度+1的新的上升子序列
                tail[++end] = nums[i];
            } else {
                //ceil()函数二分查找，在tail数组[0, end]范围内找到大于nums[i]的元素，更新其值
                int left = 0, right = end + 1;
                while (left < right) {
                    int mid = left + ((right - left) >> 1);
                    if (tail[mid] <= nums[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                if (left - 1 < 0 || tail[left - 1] != nums[i]) {  //如果nums[i]在tail数组的[0, end]范围内已经存在，什么都不做
                    tail[left] = nums[i];   //更新tail[left]值为nums[i]
                }
            }
        }
        return end + 1;
    }
}