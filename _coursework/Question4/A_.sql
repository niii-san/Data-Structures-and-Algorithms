
/*
 * Question 4 (A)
a)
Input:
Tweets table:
Write a solution to find the top 3 trending hashtags in February 2024. Every tweet may
contain several hashtags.
Return the result table ordered by count of hashtag, hashtag in descending order.
The result format is in the following example.
Explanation:

#HappyDay: Appeared in tweet IDs 13, 14, and 17, with a total count of 3 mentions.
#TechLife: Appeared in tweet IDs 16 and 18, with a total count of 2 mentions.
#WorkLife: Appeared in tweet ID 15, with a total count of 1 mention.
Note: Output table is sorted in descending order by hashtag_count and hashtag respectively.
 */


WITH Hashtags AS (
    -- Extract hashtags from tweets in February 2024
    SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(tweet, ' ', n.digit), ' ', -1) AS hashtag
    FROM Tweets
    JOIN (SELECT 1 AS digit UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 
          UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10) AS n
    ON CHAR_LENGTH(tweet) - CHAR_LENGTH(REPLACE(tweet, ' ', '')) >= n.digit - 1
    WHERE tweet LIKE '%#%' AND tweet_date BETWEEN '2024-02-01' AND '2024-02-29'
),
FilteredHashtags AS (
    -- Filter only valid hashtags
    SELECT hashtag FROM Hashtags WHERE hashtag LIKE '#%'
)
SELECT hashtag, COUNT(*) AS count
FROM FilteredHashtags
GROUP BY hashtag
ORDER BY count DESC, hashtag ASC
LIMIT 3;
