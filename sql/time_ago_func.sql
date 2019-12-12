DELIMITER $$
DROP FUNCTION IF EXISTS time_ago_func;
CREATE FUNCTION time_ago_func(TIME1 datetime , TIME2 DATETIME ) 
RETURNS VARCHAR(20)
DETERMINISTIC
BEGIN
DECLARE time_ago_str VARCHAR(20);
		if TIMESTAMPDIFF(SECOND, TIME1, TIME2) = 1 then SET time_ago_str =  '1 second ago';
  		elseif TIMESTAMPDIFF(MINUTE, TIME1, TIME2) < 1 then SET time_ago_str =  CONCAT(TIMESTAMPDIFF(second, TIME1, TIME2), ' seconds ago');
  		elseif TIMESTAMPDIFF(MINUTE, TIME1, TIME2) = 1 then SET time_ago_str =  '1 minute ago';
  		elseif TIMESTAMPDIFF(hour, TIME1, TIME2) < 1 then SET time_ago_str =  CONCAT(TIMESTAMPDIFF(MINUTE, TIME1, TIME2), ' minutes ago');
  		elseif TIMESTAMPDIFF(hour, TIME1, TIME2) = 1 then SET time_ago_str =  '1 hour ago';
		elseif TIMESTAMPDIFF(hour, TIME1, TIME2) < 24 then SET time_ago_str =  CONCAT(TIMESTAMPDIFF(HOUR, TIME1, TIME2), ' hours ago');
		elseif TIMESTAMPDIFF(DAY, TIME1, TIME2) = 1 then SET time_ago_str =  '1 day ago';
		elseif TIMESTAMPDIFF(DAY, TIME1, TIME2) < 30 then SET time_ago_str =  CONCAT(TIMESTAMPDIFF(DAY, TIME1, TIME2), ' days ago');
		elseif TIMESTAMPDIFF(MONTH, TIME1, TIME2) = 1 then SET time_ago_str =  '1 month ago';
		elseif TIMESTAMPDIFF(MONTH, TIME1, TIME2) < 12 then SET time_ago_str =  CONCAT(TIMESTAMPDIFF(MONTH, TIME1, TIME2), ' months ago');
		elseif TIMESTAMPDIFF(year, TIME1, TIME2) = 1 then SET time_ago_str =  '1 year ago';
		ELSE SET time_ago_str =  CONCAT(TIMESTAMPDIFF(year, TIME1, TIME2), ' years ago');
		END if;
RETURN time_ago_str;
END; $$
DELIMITER;
