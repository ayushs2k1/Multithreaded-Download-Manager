package IDMpackage;
import java.io.*; //Input Output data streams
import java.net.URL;
import java.net.HttpURLConnection;
import java.nio.charset.MalformedInputException;
import java.text.DecimalFormat; //Use to format numbers using a formatting pattern specified by us
import java.util.Date; //Used to display date and time
import java.util.logging.Level; //recording application activity - warning,exception information
import java.util.logging.Logger; //recording application activity - warning,exception information
public class IDM implements Runnable {//instance intended to be executed by thread
    private String urlName;
    private String OutputFileName;
    int threadNumber;
    private URL url;
    private HttpURLConnection con;
    private BufferedInputStream input;
    public BufferedOutputStream output;
    private byte[] buffer;
    int downloaded;
    int totalLength;
    int startPos = 0;
    int endPos = 0;
    int pos = 0;
    public boolean threadState;
    public static int progress = 0;
    ProgressPanel panel;
    int b;
    Date date;
    int bread = 0;
    int bytesPerSec = 0;
    public IDM(String url, ProgressPanel panel, String directory)
    { date = new Date();
//b = date.getSeconds();
        threadState = true;
        this.urlName = url;
        this.OutputFileName = directory + "\\" + url.substring(url.lastIndexOf('/') + 1);
        try {
            System.out.println("full directory with file name: " + OutputFileName);
            this.url = new URL(urlName);
            this.con = (HttpURLConnection) this.url.openConnection();
            input = new BufferedInputStream(con.getInputStream());
            totalLength = con.getContentLength();
            downloaded = 0;
            output = new BufferedOutputStream(
                    new FileOutputStream(OutputFileName));
            buffer = new byte[2 * 1024];
            this.panel = panel;
        } catch (MalformedInputException malformedInputException)
        { malformedInputException.printStackTrace();
        } catch (IOException ioException)
        { ioException.printStackTrace();
        }
    }
    public void setState(boolean state)
    { threadState = state;
    }
    public int getDownloaded()
    { return downloaded;
    }
    @Override
    public void run()
    { int bytesRead;
        try {
            while ((bytesRead = this.input.read(buffer)) != -1)
            { endPos = bytesRead;
                new IDM.ReadThreadClass(buffer, 0, bytesRead);
                System.out.println("Pos: " + startPos + ":" + endPos);
                startPos = endPos;
                this.output.flush();
            }
            this.output.flush();
            this.input.close();
            this.output.close();
        } catch (MalformedInputException malformedInputException)
        { malformedInputException.printStackTrace();
        } catch (IOException ioException)
        { ioException.printStackTrace();
        }
    }
    ProgressPanel oo = new ProgressPanel();
    class ReadThreadClass implements Runnable {
        private int startPs, endPs;
        byte[] buffewr;
        public ReadThreadClass(byte[] buffer, int startPos, int endPos)
        { startPs = startPos;
            endPs = endPos;
            buffewr = buffer;
            run();
        }
        public ReadThreadClass(int bytesRead)
        { endPs = bytesRead;
        }
        boolean a = false;
        @Override
        public void run()
        { try {
            output.write(buffer, 0, endPs);
            downloaded += endPs;
            double downloadedd = ((double) downloaded / totalLength) * 100;
            progress = (int) downloadedd;
            panel.setProgressValue(progress);
            System.out.println("progress: " + progress);
            double percentage = Double.parseDouble(new DecimalFormat(".##").format(downloadedd));
            System.out.println("downloaded: " + downloaded + " : " + (percentage) + "%");
            panel.setTextToArea(totalLength, percentage);
        } catch (IOException ex)
        { Logger.getLogger(IDM.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}
