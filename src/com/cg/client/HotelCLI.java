package com.cg.client;

import java.util.Scanner;

public class HotelCLI
{
private static Scanner console;
	
	static
	{
		console = new Scanner(System.in);
	}
	
	public static void main(String[] args) 
	{
		int option = 0;
		while(true)
		{
			System.out.println("1 - Customer");
			System.out.println("2 - Hotel Employee");
			System.out.println("3 - Admin");
			System.out.println("4 -  Exit");
			System.out.println("Enter your option : ");
			option = console.nextInt();
			
			switch(option)
			{
				case 1: 
				{
					customerChoice();
					break;
				}
				case 2: 
				{
					hotelEmployeeChoice();
					break;
				}
				case 3: 
				{
					adminChoice();
					break;
				}
				case 4: 
				{
					System.exit(0);
				}
				default:
				{
					System.out.println("Please enter the correct option");
				}
			}
		}
	}

	private static void adminChoice()
	{
		
		
	}

	private static void hotelEmployeeChoice() 
	{
		
		
	}

	private static void customerChoice() 
	{
		
		
	}

	

}
