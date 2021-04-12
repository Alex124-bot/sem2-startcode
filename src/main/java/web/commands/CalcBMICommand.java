package web.commands;

import business.exceptions.UserException;
import business.services.BmiUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcBMICommand extends CommandUnprotectedPage
{
    public CalcBMICommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        Double height = 0.0;
        Double weight = 0.0;
        Double bmi = 0.0;
        String category = "";

        try {
            height = Double.parseDouble(request.getParameter("height"));
            weight = Double.parseDouble(request.getParameter("weight"));

        }
        catch (NumberFormatException ex)
        {
            request.setAttribute("error", "Husk at du skal indtaste to heltal i formularen");
            return "index";
//            throw new UserException("Husk at du skal indtaste to heltal i formularen");
        }

        bmi = BmiUtil.calcBMI(height, weight);
        category = BmiUtil.getCategory(bmi);

        request.setAttribute("bmi", String.format("%.2f", bmi));
        request.setAttribute("height", height);
        request.setAttribute("weight", weight);
        request.setAttribute("category", category);

        return pageToShow;
    }
}
