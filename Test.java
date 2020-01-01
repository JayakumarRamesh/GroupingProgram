package com.scaalert.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		List<AlertManagement> alertManagentList = new LinkedList<>(); 
		
		
		alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-adlog-date","open",""));
		alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-dnsquery-date","open",""));
		alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-dnsquery-date","closed","w5kb"));
		alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-dnsquery-date","work in progress","w5kb"));
		
		
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","work in progress","w5t6"));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","closed","w5t6"));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","open",""));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","open",""));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","open",""));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","open",""));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","open",""));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","open",""));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","open",""));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","open",""));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","open",""));
		  alertManagentList.add(new AlertManagement("oozie-scaseclg-ERROR-sysmon-date","open",""));
		 
		
		
		Map<String,AlertManagement> alertManagementMap = new LinkedHashMap<>();
		Map<String,List<String>> userIDList = new LinkedHashMap<>();
		for(AlertManagement alertManagement:alertManagentList ) {
			if(!alertManagement.getStatus().equalsIgnoreCase("open")) {
				if(userIDList.containsKey(alertManagement.getStatus().toLowerCase())) {
					List<String> userList = userIDList.get(alertManagement.getStatus());
						userList.add(alertManagement.getUserId().toLowerCase());
					userIDList.put(alertManagement.getStatus(),userList);
				}else {
					List<String> userList = new LinkedList<>();
					userList.add(alertManagement.getUserId().toLowerCase());
					userIDList.put(alertManagement.getStatus(),userList);
				}
			}
			
			if(alertManagementMap.containsKey(alertManagement.getRowKey().split("-")[3])) {
				AlertManagement obj = new AlertManagement();
				String currentStatus = alertManagement.getStatus();
				String statusFromMap = alertManagementMap.get(alertManagement.getRowKey().split("-")[3]).getStatus();
				String userIdFromMap = alertManagementMap.get(alertManagement.getRowKey().split("-")[3]).getUserId();
				String status = currentStatus+","+statusFromMap;
				obj.setStatus(status.toLowerCase());
				if(alertManagement.getStatus().equalsIgnoreCase("open")) {
					if(userIdFromMap!=null && !userIdFromMap.trim().equalsIgnoreCase("")) {
						obj.setUserId(userIdFromMap.toLowerCase());
					}else {
						obj.setUserId("");
					}
				}else {
					String CurrentUserId = alertManagement.getUserId();
					if(userIdFromMap!=null && !userIdFromMap.trim().equalsIgnoreCase("")) {
						CurrentUserId+=","+userIdFromMap;
					}
					obj.setUserId(CurrentUserId.toLowerCase());
				}
				obj.setRowKey(alertManagement.getRowKey());
				alertManagementMap.replace(alertManagement.getRowKey().split("-")[3], obj);
			}else {
				AlertManagement obj = new AlertManagement();
				obj.setRowKey(alertManagement.getRowKey());
				obj.setStatus(alertManagement.getStatus().toLowerCase());
				if(alertManagement.getUserId()!=null && !alertManagement.getUserId().equalsIgnoreCase("")){
					obj.setUserId(alertManagement.getUserId());
				}else {
					obj.setUserId("");
				}
				alertManagementMap.put(alertManagement.getRowKey().split("-")[3], obj);
			}
		}
		List<AlertManagement> generatedAlertManagementList = new LinkedList<>();
		for(String sourceName:alertManagementMap.keySet()) {
			System.out.println(sourceName+" ==> "+alertManagementMap.get(sourceName));
			AlertManagement alertManagement = alertManagementMap.get(sourceName);
			if(Arrays.asList(alertManagement.getStatus().split(",")).stream().distinct().count()<=1) {
				alertManagement.setStatus(alertManagement.getStatus().split("-")[0]);
			}else {
				alertManagement.setStatus("inprogress");
			}
			generatedAlertManagementList.add(alertManagement);
		}
		System.out.println("-----------------FinalList-------------------------");
		for(AlertManagement alert:generatedAlertManagementList) {
			System.out.println(alert);
		}
		
		System.out.println("--------------------------userlist----------------");
		for(String status:userIDList.keySet()) {
			System.out.println(status+" ==> "+userIDList.get(status));
		}
	}

}
