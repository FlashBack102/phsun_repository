package com.example.start.guest.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

public interface GuestService {

	public int register(HttpServletRequest req, Model model);
}
