using System.Globalization;
using System.Windows;
using Microsoft.Phone.Controls;

namespace CurrencyConverter
{
    public partial class MainPage : PhoneApplicationPage
    {
        // Constructor
        public MainPage()
        {
            InitializeComponent();

        }

        private void ConvertButton_Click(object sender, RoutedEventArgs e)
        {
            if (CurrancyComboBox.SelectionBoxItem == null)
            {
                ConvertedTextBox.Text = ValueTextBox.Text;
                return;
            }
            switch (CurrancyComboBox.SelectionBoxItem.ToString())
            {
                case "EUR (Euro)":
                    ConvertedTextBox.Text = (double.Parse(ValueTextBox.Text) / 1.9569).ToString(CultureInfo.InvariantCulture);
                    break;

                case "USD (US Dollar)":
                    ConvertedTextBox.Text = (double.Parse(ValueTextBox.Text) / 1.7636).ToString(CultureInfo.InvariantCulture);
                    break;

                case "GBP (British pound)":
                    ConvertedTextBox.Text = (double.Parse(ValueTextBox.Text) / 2.52056).ToString(CultureInfo.InvariantCulture);
                    break;

                default:
                    ConvertedTextBox.Text = ValueTextBox.Text;
                    break;
            }
        }
    }
}