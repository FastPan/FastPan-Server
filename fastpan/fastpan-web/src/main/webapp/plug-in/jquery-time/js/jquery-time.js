(function($) {
	$
			.extend({
				myTime : {
					/**
					 * 当前时间戳
					 * 
					 * @return <int> unix时间戳(秒)
					 */
					CurTime : function() {
						return Date.parse(new Date()) / 1000;
					},
					/**
					 * 日期 转换为 Unix时间戳
					 * 
					 * @param <string>
					 *            2014-01-01 20:20:20 日期格式
					 * @return <int> unix时间戳(毫秒)
					 */
					DateToUnix : function(string) {
						var f = string.split(' ', 2);
						var d = (f[0] ? f[0] : '').split('-', 3);
						var t = (f[1] ? f[1] : '').split(':', 3);
						return (new Date(parseInt(d[0], 10) || null, (parseInt(
								d[1], 10) || 1) - 1,
								parseInt(d[2], 10) || null, parseInt(t[0], 10)
										|| null, parseInt(t[1], 10) || null,
								parseInt(t[2], 10) || null)).getTime();
					},
					/**
					 * 时间戳转换日期
					 * 
					 * @param <long>
					 *            unixTime 待时间戳(毫秒)
					 */
					UnixToDate : function(unixTime) {
						return new Date(unixTime).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ").replace("下午"," ");
					}
				}
			});
})(jQuery);