package test;

import my.dao.utils.DBHelper;

public class DaoTest {
	public static void main(String[] args) {

		DBHelper helper = new DBHelper();
		
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:root-context.xml");
//		List<ImUser> users = new ArrayList<>();
//		//用户
//		for (int i = 1; i < 10; i++) {
//			ImUser user = new ImUser();
//			//user.setUserid("" + i);
//			user.setNodeid(i+"");
//			user.setUsername("user" + i);
//			user.setUsernamepinyin("user"+i);
//			user.setUsernamepy("user"+i);
//			user.setMobileno("1368104056"+i);
//			user.setUserpwd("test");
//			user.setUsertype(i/2==0?"0":"1");
//			user.setOrgantype(i/2==0?"0":"");
//			user.setServicetype("0");
//			user.setPhotourl("https://www.baidu.com/img/bdlogo.png");
//			user.insert();
//			users.add(user);
//		}
//		
//		//群组
//		for (int i = 1; i < 10; i++) {
//			ImGroup group = new ImGroup();
//			//group.setGroupid("" + i);
//			group.setGroupname("测试群组" + i);
//			group.setCreate_time(DateUtils.getCurrentDateTimeStr());
//			group.setCreater("" + i);
//			group.setType(ImGroup.TYPE_GROUP);
//			group.insert();
//
//			for (ImUser user:users) {
//				ImGroupUser imGroupUser = new ImGroupUser();
//				imGroupUser.setGroupid(group.getGroupid());
//				imGroupUser.setUserid(user.getUserid());
//				imGroupUser.insert();
//			}
//		}
//		
//		//圈子
//		for (int i = 10; i < 20; i++) {
//			ImGroup group = new ImGroup();
//			//group.setGroupid("" + i);
//			group.setGroupname("测试圈子" + i);
//			group.setCreate_time(DateUtils.getCurrentDateTimeStr());
//			group.setCreater("" + i);
//			group.setType(ImGroup.TYPE_QUANZI);
//			group.insert();
//
//			for (ImUser user:users) {
//				ImGroupUser imGroupUser = new ImGroupUser();
//				imGroupUser.setGroupid(group.getGroupid());
//				imGroupUser.setUserid(user.getUserid());
//				imGroupUser.insert();
//			}
//		}
//		
//		//关注
//		for (ImUser from:users) {
//			for (ImUser to:users) {
//				if (!from.getUserid().equals(to.getUserid())) {
//					ImFriendlist.INSTANCE.delete("fromuserid=? and touserid=?", from.getUserid(), to.getUserid());
//					ImFriendlist list = new ImFriendlist();
//					list.setFromuserid(from.getUserid());
//					list.setTouserid(to.getUserid());
//					list.insert();
//				}
//			}
//		}
//
//		DBManager.commitAll();
//
//		DBManager.closeAllConnection();
//
//		DBManager.closeDataSource();
//
//		CacheManager.stop();
		
		
		//context.close();

		// for(int i=1;i<10;i++){
		// ImUser user = new ImUser();
		// user.setUserid(""+i);
		// user.setUsername("user"+i);
		// user.setUserpassword("test");
		// user.insert();
		// }

		// for(int i=1;i<10;i++){
		// ImGroup group = new ImGroup();
		// group.setGroupid(""+i);
		// group.setGroupname("测试群组"+i);
		// group.setCreate_time(DateUtils.getCurrentDateTimeStr());
		// group.setCreater(""+i);
		// group.setType(ImGroup.TYPE_GROUP);
		// group.insert();
		//
		// for(int j=1;j<10;j++){
		// ImGroupUser imGroupUser = new ImGroupUser();
		// imGroupUser.setGroupid(group.getGroupid());
		// imGroupUser.setUserid(""+j);
		// imGroupUser.insert();
		// }
		// }
		//
		// DBManager.commitAll();
		// DBManager.closeAllConnection();

		// Ticket.start("my.im.dbo");
		//
		// ImFriendlist list = new ImFriendlist();
		// list.setFromuserid("1");
		// list.setTouserid("2");
		// //list.setId(Ticket.genId("IM_FRIENDLIST")+"");
		// list.insert();
		// DBManager.commitAll();
		// list = new ImFriendlist();
		// list.setFromuserid("1");
		// list.setTouserid("3");
		// list.setId(Ticket.genId("IM_FRIENDLIST")+"");
		// list.insert();
		//
		//
		// list = new ImFriendlist();
		// list.setFromuserid("4");
		// list.setTouserid("1");
		// list.setId(Ticket.genId("IM_FRIENDLIST")+"");
		// list.insert();
		//
		// list = new ImFriendlist();
		// list.setFromuserid("5");
		// list.setTouserid("1");
		// list.setId(Ticket.genId("IM_FRIENDLIST")+"");
		// list.insert();

//		List<ImFriendlist> list = ImFriendlist.INSTANCE.query("fromuserid=?", "1");
//
//		System.out.println(list.size());
//
//		list = ImFriendlist.INSTANCE.query("fromuserid=?", "1");
//
//		System.out.println(list.size());
//
//		DBManager.commitAll();
//		DBManager.closeAllConnection();
	}
}
